package com.payment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payment.model.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
	
	public Card findByCardNumber(long cardNumber);

}
