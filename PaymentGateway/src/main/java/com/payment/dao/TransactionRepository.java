//IT19014128
//A.M.W.W.R.L. Wataketiya

package com.payment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payment.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
