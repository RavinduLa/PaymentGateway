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
	
	
	//Repositories are initialized in the constructor
	@Autowired
	public PaymentApi(CardRepository cardRepository, TransactionRepository transactionrepository) {
		this.cardRepository = cardRepository;
		
		this.transactionrepository = transactionrepository;
	}
	
	//making the payment
	public PaymentResponse makePayment(Payment payment) {
		System.out.println();
		System.out.println("*****Paytime Payment Gateway*****");
		System.out.println("Payment request received");
		System.out.println("Initiating process....");
		
		//payment response object instantiation
		PaymentResponse paymentResponse = new PaymentResponse();
		
		//assign the user provided details to variables
		long userCardNumber = payment.getUsercardNumber();
		int userExpirationYear = payment.getUserExpirationYear();
		int userExpirationMonth = payment.getUserExpirationmonth();
		int userCvc = payment.getUserCvc();
		double paymentAmount = payment.getAmount();
		
		//String cardType;
		
		//setting the card on the received card number
		Card card = cardRepository.findByCardNumber(userCardNumber);
		
		//if the card is null set the status as invalid and message as invalid card number in the reponse
		if(card == null) {
			System.out.println("The entered card number,"+ userCardNumber+" does not match any card in the system.");
			paymentResponse.setStatus("falied");
			paymentResponse.setMessage("Invalid Card Number");
			paymentResponse.setValidCard(false);
			paymentResponse.setPaymentSuccesful(false);
			return paymentResponse;
		}
		else {
			
			//card number is available in the schema.
			//now validate the card
			boolean cardValidated;
			cardValidated = validateCard(userCardNumber, userExpirationYear, userExpirationMonth, userCvc, card);
			
			//if the card is valid proceed to transaction
			if(cardValidated) {
				System.out.println("Card succesfully validated. Proceeding to transaction");
				//call the processTransaction and set the result in a boolean variable
				boolean transactionCompleted = processTransaction(paymentAmount, userCardNumber, payment.getMerchantId()); //dummy call to make payment.
				
				//if transaction is complete mark the payment and transaction as successful
				//and the card as valid in the response.
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
					//Transaction could not be succesfully completed
					//card details validated
					//most probably an internal error
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
				
				//card details are invalid
				System.out.println("Card could not be validated. Returning false to the merchant.");
				paymentResponse.setStatus("falied");
				paymentResponse.setMessage("Payment failed");
				paymentResponse.setValidCard(false);
				paymentResponse.setPaymentSuccesful(false);
				return paymentResponse;
			}
			
		}
	}
	
	//validates the card details
public boolean validateCard(long userCardNumber, int userExpirationYear, int userExpirationMonth,int userCvc, Card card) {
		
		boolean cardDetailsValid,cardEnabled,cardNonExpired;
		//validates whether the user entered card details matches with the schema
		cardDetailsValid = cardValidator.validateCardDetails(userCardNumber, userExpirationYear, userExpirationMonth, userCvc, card);
		//validates whether the card is enabled
		cardEnabled = cardValidator.validateCardEnablement(card);
		//validates that the card is not expired
		cardNonExpired = cardValidator.isCardNonExpired(card);
		
		//if only all 3 are validated the card will be marked as validated
		if(cardDetailsValid && cardEnabled && cardNonExpired) {
			System.out.println("Card validation chain succesfully completed.");
			return true;
		}
		else {
			//even if one condition fails card is invalid
			System.out.println("Card validation chain failed.");
			return false;
		}
	}

//making the transaction
public boolean processTransaction(double amount, long cardNumber, int merchantId) {
	
	System.out.println("Initiating transaction....");
	
	Calendar current = Calendar.getInstance();
	
	//part of bank done here.
	
	//new transaction object is initiated. Details are set to the object.
	Transaction newTransaction = new Transaction();
	newTransaction.setCardNumber(cardNumber);
	newTransaction.setAmount(amount);
	newTransaction.setMerchant(merchantId);
	newTransaction.setTransactionTime(current);
	
	if(transactionrepository.save(newTransaction) != null) {
		
		//transaction is successful
		System.out.println("Transaction succesfully registered.");
		return true;
	}
	else {
		//transaction is failed.
		System.out.println("Error in registering the transaction.");
		return false;
	}
	
	
}
	

}
