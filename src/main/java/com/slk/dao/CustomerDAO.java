package com.slk.dao;


import java.util.List;
import org.springframework.stereotype.Repository;

import com.slk.model.Customer;
import com.slk.model.Loan;
import com.slk.model.Transaction;

@Repository
public interface CustomerDAO {
	Customer viewProfile(int id);
	List<Customer> viewAllCustomer();
	public double viewBalance(int id);
	Loan viewLoan(int id);
	void updateprofile(int id,Customer cust);
	List list();
	List<Transaction> transactionhistory(int id);
	public int transfer(int id, double amount, int account_number);

	
}
