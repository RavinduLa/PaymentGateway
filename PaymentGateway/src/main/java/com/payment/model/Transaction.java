package com.payment.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transactions")
public class Transaction {
	
	@Id
	@Column(name="transaction_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	
	@Column(name="card_number")
	private long cardNumber;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="merchant")
	private int merchant;
	
	@Column(name="transaction_time")
	private Calendar transactionTime;
	
	public Transaction() {
		
	}

	public Transaction(int transactionId, long cardNumber, double amount, int merchant, Calendar transactionTime) {
		super();
		this.transactionId = transactionId;
		this.cardNumber = cardNumber;
		this.amount = amount;
		this.merchant = merchant;
		this.transactionTime = transactionTime;
	}



	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getMerchant() {
		return merchant;
	}

	public void setMerchant(int merchant) {
		this.merchant = merchant;
	}

	public Calendar getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Calendar transactionTime) {
		this.transactionTime = transactionTime;
	}
	

	public int getTransactionId() {
		return transactionId;
	}



	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}



	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", cardNumber=" + cardNumber + ", amount=" + amount
				+ ", merchant=" + merchant + ", transactionTime=" + transactionTime + "]";
	}
	
	
	
	

}
