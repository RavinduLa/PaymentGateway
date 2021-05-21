//IT19014128
//A.M.W.W.R.L. Wataketiya

package com.payment.controller;

import com.payment.model.Card;

public interface CardValidator {
	
	public boolean validateCardEnablement(Card card);
	public boolean isCardNonExpired(Card card);
	public boolean validateCardDetails(long userCardNumber, int userExpirationYear, int userExpirationMonth,int userCvc, Card card);

}
