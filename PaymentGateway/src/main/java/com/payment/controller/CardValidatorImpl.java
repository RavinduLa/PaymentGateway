package com.payment.controller;

import java.util.Calendar;
import java.util.Date;

import com.payment.model.Card;

public class CardValidatorImpl implements CardValidator {

	//checks whether the card is enabled
	@Override
	public boolean validateCardEnablement(Card card) {
		
		if(card.isEnabled()   ) {
			
			//here a double-check happens
			//enabled boolean value is checked
			//status is checked 
			//both thse values are checked whether they match
			
			if(card.getStatus().toLowerCase().equals("enabled")) {
				System.out.println("Card marked enabled true. Card status is enabled");
				return true;
			}
			else {
				
				//here, card is boolean enabled 
				//but card status contradicts with the boolean value
				//raise an alarm
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
			//card is not enabled.
			System.out.println("Card is not enabled");
			return false;
		}
		
	}

	//checks whether the card is expired
	@Override
	public boolean isCardNonExpired(Card card) {
		
		int cardYear,cardMonth;
		//get the expiration year and month
		cardYear = card.getExpirationYear();
		cardMonth = card.getExpirationMonth();
		
		//get the current time Calendar instance
		Calendar current = Calendar.getInstance();
		
		//instantiate expiration date
		Calendar expirationDate = Calendar.getInstance();
		
		//set the expiration date using card expiration year and month
		expirationDate.set(cardYear, cardMonth, 1);
		
		//check whether the current date is before the expired date.
		if(current.before(expirationDate)) {
			//card is not expired
			System.out.println("Card is not expired");
			return true;
		}
		else {
			//card is expired.
			System.out.println("Card is expired");
			return false;
		}
		
	}

	//user entered details are validated against the schema details of the card.
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
						//if all the user entered details are matched, true is returned.
						//even if one is mismatching false is returned
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
		
		else //card numbers did not match.
		{
			System.out.println("Card Numbers did not match.");
			System.out.println("User entered Card Number: "+ userCardNumber);
			System.out.println("System stored Card Number: "+ card.getCardNumber());
			return false;
			
		}
	}

}
