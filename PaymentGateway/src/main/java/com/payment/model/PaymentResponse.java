package com.payment.model;

import org.springframework.stereotype.Component;

@Component
public class PaymentResponse {
	
	private String status;
	private boolean validCard;
	private boolean paymentSuccesful;
	private String message;
	
	public PaymentResponse() {
		
	}
	
	

	public PaymentResponse(String status, boolean validCard, boolean paymentSuccesful, String message) {
		super();
		this.status = status;
		this.validCard = validCard;
		this.paymentSuccesful = paymentSuccesful;
		this.message = message;
	}



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

	public boolean isValidCard() {
		return validCard;
	}



	public void setValidCard(boolean validCard) {
		this.validCard = validCard;
	}



	public boolean isPaymentSuccesful() {
		return paymentSuccesful;
	}



	public void setPaymentSuccesful(boolean paymentSuccesful) {
		this.paymentSuccesful = paymentSuccesful;
	}



	@Override
	public String toString() {
		return "PaymentResponse [status=" + status + ", validCard=" + validCard + ", paymentSuccesful="
				+ paymentSuccesful + ", message=" + message + "]";
	}
	
	
	
	

}
