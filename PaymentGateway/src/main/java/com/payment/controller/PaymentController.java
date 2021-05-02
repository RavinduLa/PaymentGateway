package com.payment.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.dao.CardRepository;
import com.payment.dao.TransactionRepository;
import com.payment.model.Card;
import com.payment.model.Payment;
import com.payment.model.Transaction;

@RestController
@RequestMapping("paytime/api/")
@CrossOrigin(origins ="*",allowedHeaders = "*",exposedHeaders = "*")
public class PaymentController {
	
	@Autowired
	CardRepository cardRepository;
	
	@Autowired
	TransactionRepository transactionrepository;
	
	CardValidator cardValidator = new CardValidatorImpl();
	
	
	@PostMapping(value = "makePayment")
	public boolean makePayment(@RequestBody Payment payment) {
		long userCardNumber = payment.getUsercardNumber();
		int userExpirationYear = payment.getUserExpirationYear();
		int userExpirationMonth = payment.getUserExpirationmonth();
		int userCvc = payment.getUserCvc();
		double paymentAmount = payment.getAmount();
		
		String cardType;
		
		//setting the card on the received card number
		Card card = cardRepository.findByCardNumber(userCardNumber);
		
		if(card == null) {
			System.out.println("The entered card number,"+ userCardNumber+" does not match any card in the system.");
			return false;
		}
		else {
			
			boolean cardValidated;
			cardValidated = validateCard(userCardNumber, userExpirationYear, userExpirationMonth, userCvc, card);
			
			if(cardValidated) {
				System.out.println("Card succesfully validated. Returning true the merchant");
				processTransaction(paymentAmount, userCardNumber, payment.getMerchantId()); //dummy call to make payment.
				return true;
			}
			else {
				System.out.println("Card could not be validated. Returning false to the merchant.");
				return false;
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
	
	public void transferFunds() {
		//call process transaction
		//if transaction is successfull call sms and email
	}
	
	public boolean processTransaction(double amount, long cardNumber, int merchantId) {
		
		Calendar current = Calendar.getInstance();
		
		//part of bank
		
		Transaction newTransaction = new Transaction();
		newTransaction.setCardNumber(cardNumber);
		newTransaction.setAmount(amount);
		newTransaction.setMerchant(merchantId);
		newTransaction.setTransactionTime(current);
		
		if(transactionrepository.save(newTransaction) != null) {
			return true;
		}
		else {
			return false;
		}
		
		
	}
	
	
	

}
