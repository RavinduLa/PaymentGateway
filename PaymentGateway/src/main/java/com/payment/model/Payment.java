package com.payment.model;

public class Payment {
	
	private long usercardNumber;
	private int userExpirationYear;
	private int userExpirationmonth;
	private int userCvc;
	private int merchantId;
	private double amount;
	
	public Payment() {
		
	}
	
	

	public Payment(long usercardNumber, int userExpirationYear, int userExpirationmonth, int userCvc, int merchantId,
			double amount) {
		super();
		this.usercardNumber = usercardNumber;
		this.userExpirationYear = userExpirationYear;
		this.userExpirationmonth = userExpirationmonth;
		this.userCvc = userCvc;
		this.merchantId = merchantId;
		this.amount = amount;
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


	@Override
	public String toString() {
		return "Payment [usercardNumber=" + usercardNumber + ", userExpirationYear=" + userExpirationYear
				+ ", userExpirationmonth=" + userExpirationmonth + ", userCvc=" + userCvc + ", merchantId=" + merchantId
				+ ", amount=" + amount + "]";
	}
	
	
	
	
	

}
