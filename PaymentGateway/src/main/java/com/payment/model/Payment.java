//IT19014128
//A.M.W.W.R.L. Wataketiya

package com.payment.model;

public class Payment {
	
	private long usercardNumber; //user entered card number
	private int userExpirationYear; //user entered expiration year
	private int userExpirationmonth; //user entered expiration month
	private int userCvc; //user entered cvc
	private int merchantId;  //the merchant id -merchant must provide this
	private double amount;  //calculated amount agreed by user and merchant
	private String cardType;  //card type -visa, master etc.
	
	public Payment() {
		
	}
	
	

	



	public Payment(long usercardNumber, int userExpirationYear, int userExpirationmonth, int userCvc, int merchantId,
			double amount, String cardType) {
		super();
		this.usercardNumber = usercardNumber;
		this.userExpirationYear = userExpirationYear;
		this.userExpirationmonth = userExpirationmonth;
		this.userCvc = userCvc;
		this.merchantId = merchantId;
		this.amount = amount;
		this.cardType = cardType;
	}







	public long getUsercardNumber() {
		return usercardNumber;
	}

	public void setUsercardNumber(long usercardNumber) {
		this.usercardNumber = usercardNumber;
	}

	public int getUserExpirationYear() {
		return userExpirationYear;
	}

	public void setUserExpirationYear(int userExpirationYear) {
		this.userExpirationYear = userExpirationYear;
	}

	public int getUserExpirationmonth() {
		return userExpirationmonth;
	}

	public void setUserExpirationmonth(int userExpirationmonth) {
		this.userExpirationmonth = userExpirationmonth;
	}

	public int getUserCvc() {
		return userCvc;
	}

	public void setUserCvc(int userCvc) {
		this.userCvc = userCvc;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	

	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}
	
	


	public String getCardType() {
		return cardType;
	}


	public void setCardType(String cardType) {
		this.cardType = cardType;
	}







	@Override
	public String toString() {
		return "Payment [usercardNumber=" + usercardNumber + ", userExpirationYear=" + userExpirationYear
				+ ", userExpirationmonth=" + userExpirationmonth + ", userCvc=" + userCvc + ", merchantId=" + merchantId
				+ ", amount=" + amount + ", cardType=" + cardType + "]";
	}
	
	
	
	
	

}
