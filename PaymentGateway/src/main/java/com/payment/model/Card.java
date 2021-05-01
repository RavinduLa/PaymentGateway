package com.payment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="card")
public class Card {

	@Id
	@Column(name="card_number")
	private long cardNumber;//16 digit unique number
	@Column(name="expiration_year")
	private int expirationYear;
	@Column(name="expiration_month")
	private int expirationMonth;
	@Column(name="cvc")
	private int cvc; // 3 digit number
	@Column(name="card_holder")
	private String cardHolder; // name of the card holder
	@Column(name="card_name")
	private String cardName; // visa or master card
	@Column(name="card_type")
	private String cardType; // credit or debit card
	@Column(name="enabled")
	private boolean enabled; //is card blocked or not
	@Column(name="status")
	private String status; //card status
	
	public Card() {
		
	}
	

	

	public Card(long cardNumber, int expirationYear, int expirationMonth, int cvc, String cardHolder, String cardName,
			String cardType, boolean enabled, String status) {
		super();
		this.cardNumber = cardNumber;
		this.expirationYear = expirationYear;
		this.expirationMonth = expirationMonth;
		this.cvc = cvc;
		this.cardHolder = cardHolder;
		this.cardName = cardName;
		this.cardType = cardType;
		this.enabled = enabled;
		this.status = status;
	}




	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}

	public int getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public int getCvc() {
		return cvc;
	}

	public void setCvc(int cvc) {
		this.cvc = cvc;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
	
	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}




	@Override
	public String toString() {
		return "Card [cardNumber=" + cardNumber + ", expirationYear=" + expirationYear + ", expirationMonth="
				+ expirationMonth + ", cvc=" + cvc + ", cardHolder=" + cardHolder + ", cardName=" + cardName
				+ ", cardType=" + cardType + ", enabled=" + enabled + ", status=" + status + "]";
	}
	
	
	
	
}
