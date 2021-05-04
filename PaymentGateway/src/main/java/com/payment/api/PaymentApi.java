package com.payment.api;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.controller.CardValidator;
import com.payment.controller.CardValidatorImpl;
import com.payment.dao.CardRepository;
import com.payment.dao.TransactionRepository;
import com.payment.model.Card;
import com.payment.model.Payment;
import com.payment.model.PaymentResponse;
import com.payment.model.Transaction;

@Service
public class PaymentApi {
	
	private CardRepository cardRepository;
	
	private TransactionRepository transactionrepository;
	
	CardValidator cardValidator = new CardValidatorImpl();
	
	@Autowired
	public PaymentApi(CardRepository cardRepository, TransactionRepository transactionrepository) {
		this.cardRepository = cardRepository;
		
		this.transactionrepository = transactionrepository;
	}
	
	public PaymentResponse makePayment(Payment payment) {
		System.out.println();
		System.out.println("*****Paytime Payment Gateway*****");
		System.out.println("Payment request received");
		System.out.println("Initiating process....");
		
		
		PaymentResponse paymentResponse = new PaymentResponse();
		
		long userCardNumber = payment.getUsercardNumber();
		int userExpirationYear = payment.getUserExpirationYear();
		int userExpirationMonth = payment.getUserExpirationmonth();
		int userCvc = payment.getUserCvc();
		double paymentAmount = payment.getAmount();
		
		//String cardType;
		
		//setting the card on the received card number
		Card card = cardRepository.findByCardNumber(userCardNumber);
		
		if(card == null) {
			System.out.println("The entered card number,"+ userCardNumber+" does not match any card in the system.");
			paymentResponse.setStatus("falied");
			paymentResponse.setMessage("Invalid Card Number");
			paymentResponse.setValidCard(false);
			paymentResponse.setPaymentSuccesful(false);
			return paymentResponse;
		}
		else {
			
			boolean cardValidated;
			cardValidated = validateCard(userCardNumber, userExpirationYear, userExpirationMonth, userCvc, card);
			
			if(cardValidated) {
				System.out.println("Card succesfully validated. Proceeding to transaction");
				boolean transactionCompleted = processTransaction(paymentAmount, userCardNumber, payment.getMerchantId()); //dummy call to make payment.
				
				if(transactionCompleted)
				{
					System.out.println("Transaction succesfully completed.");
					System.out.println("Returning success to the merchant");
					paymentResponse.setStatus("success");
					paymentResponse.setMessage("Payment Succesfull");
					paymentResponse.setValidCard(true);
					paymentResponse.setPaymentSuccesful(true);
					return paymentResponse;
				}
				else
				{
					System.out.println("Error in processing transaction.");
					System.out.println("Returning falied to the merchant.");
				
					paymentResponse.setStatus("failed");
					paymentResponse.setMessage("Payment Failed");
					paymentResponse.setValidCard(true);
					paymentResponse.setPaymentSuccesful(false);
					return paymentResponse;
				}
				
				
			}
			else {
				System.out.println("Card could not be validated. Returning false to the merchant.");
				paymentResponse.setStatus("falied");
				paymentResponse.setMessage("Payment failed");
				paymentResponse.setValidCard(false);
				paymentResponse.setPaymentSuccesful(false);
				return paymentResponse;
			}
			
		}
	}
	
public boolean validateCard(long userCardNumber, int userExpirationYear, int userExpirationMonth,int userCvc, Card card) {
		
		boolean cardDetailsValid,cardEnabled,cardNonExpired;
		cardDetailsValid = cardValidator.validateCardDetails(userCardNumber, userExpirationYear, userExpirationMonth, userCvc, card);
		cardEnabled = cardValidator.validateCardEnablement(card);
		cardNonExpired = cardValidator.isCardNonExpired(card);
		
		if(cardDetailsValid && cardEnabled && cardNonExpired) {
			System.out.println("Card validation chain succesfully completed.");
			return true;
		}
		else {
			System.out.println("Card validation chain failed.");
			return false;
		}
	}

public boolean processTransaction(double amount, long cardNumber, int merchantId) {
	
	System.out.println("Initiating transaction....");
	
	Calendar current = Calendar.getInstance();
	
	//part of bank
	
	Transaction newTransaction = new Transaction();
	newTransaction.setCardNumber(cardNumber);
	newTransaction.setAmount(amount);
	newTransaction.setMerchant(merchantId);
	newTransaction.setTransactionTime(current);
	
	if(transactionrepository.save(newTransaction) != null) {
		System.out.println("Transaction succesfully registered.");
		return true;
	}
	else {
		System.out.println("Error in registering the transaction.");
		return false;
	}
	
	
}
	

}
