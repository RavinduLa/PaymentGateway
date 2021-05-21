//IT19014128
//A.M.W.W.R.L. Wataketiya

package com.payment.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.api.PaymentApi;

import com.payment.model.Payment;
import com.payment.model.PaymentResponse;


@RestController
@RequestMapping("paytime/api")
@CrossOrigin(origins ="*",allowedHeaders = "*",exposedHeaders = "*")
public class PaymentController {
	
	@Autowired
	private PaymentResponse paymentResponse;
	
	private PaymentApi paymentApi;
	
	@Autowired
	public PaymentController(PaymentApi paymentApi) {
		this.paymentApi = paymentApi;
		//this.paymentResponse = paymentResponse;
	}
	
	
	//makePayment POST call is received here
	@PostMapping(value = "makePayment")
	public PaymentResponse makePayment(@RequestBody Payment payment) {
		
		//call payment API
		paymentResponse=  paymentApi.makePayment(payment);
	
		//return the response
		return paymentResponse;
		
	}
	
	
	

}
