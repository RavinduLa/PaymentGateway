package com.payment.controller;

import java.util.Calendar;
import java.util.Date;

import com.payment.model.Card;

public class CardValidatorImpl implements CardValidator {

	@Override
	public boolean validateCardEnablement(Card card) {
		
		if(card.isEnabled()   ) {
			
			if(card.getStatus().toLowerCase().equals("enabled")) {
				System.out.println("Card marked enabled true. Card status is enabled");
				return true;
			}
			else {
				System.out.println();
				System.out.println("!!!CHECK!!!");
				System.out.println("Card marked enabled true.");
				System.out.println("But, Card status does not match!");
				System.out.println(" Card status is: "+ card.getStatus());
				System.out.println();
				return false;
			}
			
		}
		else {
			
			System.out.println("Card is not enabled");
			return false;
		}
		
	}

	@Override
	public boolean isCardNonExpired(Card card) {
		
		int cardYear,cardMonth;
		cardYear = card.getExpirationYear();
		cardMonth = card.getExpirationMonth();
		
		Calendar current = Calendar.getInstance();
		Calendar expirationDate = Calendar.getInstance();
		
		expirationDate.set(cardYear, cardMonth, 1);
		
		if(current.before(expirationDate)) {
			System.out.println("Card is not expired");
			return true;
		}
		else {
			System.out.println("Card is expired");
			return false;
		}
		
	}

	@Override
	public boolean validateCardDetails(long userCardNumber, int userExpirationYear, int userExpirationMonth,
			int userCvc, Card card) {
		if(userCardNumber == card.getCardNumber()) {
			if(userCvc == card.getCvc()) 
			{
				if(userExpirationYear == card.getExpirationYear())
				{
					if(userExpirationMonth == card.getExpirationMonth()) 
					{
						System.out.println("All Parameters matched. Card Details are valid.");
						return true;
					}
					else //expiration month did not match
					{
						System.out.println("Expiration months did not match.");
						System.out.println("User entered month: "+ userExpirationMonth);
						System.out.println("System stored month: "+ card.getExpirationMonth());
						return false;
					}
				}
				else //expiration years did not match
				{
					System.out.println("Expiration years did not match.");
					System.out.println("User entered year: "+ userExpirationYear);
					System.out.println("System stored year: "+ card.getExpirationYear());
					return false;
				}
			}
			else //CVCS did not match
			{
				System.out.println("CVCs did not match.");
				System.out.println("User entered CVC: "+ userCvc);
				System.out.println("System stored CVC: "+ card.getCvc());
				return false;
			}
		}
		
		else
		{
			System.out.println("Card Numbers did not match.");
			System.out.println("User entered Card Number: "+ userCardNumber);
			System.out.println("System stored Card Number: "+ card.getCardNumber());
			return false;
			
		}
	}

}
