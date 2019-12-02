package com.slk.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.slk.dao.CustomerDAO;
import com.slk.dao.CustomerDaoImpl;
import com.slk.model.Customer;
import com.slk.model.Loan;
import com.slk.model.Transaction;
@Repository

@RestController
//@RequestMapping("/customerService")
public class CustomerRestController {
	
	@Autowired
	CustomerDAO customerDAO;
	

	@GetMapping("/Customer")
		public List getCustomers() {
		
		return customerDAO.viewAllCustomer();
	}
	
	//PROFILE
	@GetMapping("/Customer/{id}")
	public ResponseEntity getCustomer(@PathVariable("id") int id) {

		Customer customer = customerDAO.viewProfile(id);
		if (customer == null) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(customer, HttpStatus.OK);
	}
	//Balance
	@GetMapping("/Customer/Balance/{id}")
	public ResponseEntity getCustomerBalance(@PathVariable("id") int id) {

	double customer = customerDAO.viewBalance(id);
		if (customer == 0) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(customer, HttpStatus.OK);
	}
	
	//Loan
		@GetMapping("/Customer/Loan/{id}")
		public ResponseEntity getCustomerLoan(@PathVariable("id") int id) {

		Loan loan = customerDAO.viewLoan(id);
			if (loan ==null) {
				return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity(loan, HttpStatus.OK);
		}
		
		//Update
		@PutMapping(value="/put/Customer/{id}")
		public ResponseEntity createCustomer(@PathVariable("id") int id,@RequestBody Customer cust) {

			customerDAO.updateprofile(id,cust);

			return new ResponseEntity(cust, HttpStatus.OK);
		}
		
		//TRANSACTION
		@GetMapping("/Customer/Transaction/{id}")
		public ResponseEntity getCustomerTransaction(@PathVariable("id") int id) {

		List<Transaction> t = customerDAO.transactionhistory(id);
			if (t == null) {
				return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity(t, HttpStatus.OK);
		}
		
		//TRANSFER
		@GetMapping("/Customer/Transfer/{id}{amount}{account_number}")
		public ResponseEntity getCustomerTransfer(@PathVariable("id") int id,@PathVariable("amount") double amount,@PathVariable("account_number") int account_number) {
			//double amount=500.00;
			//int account_number=123456;
			int flag = customerDAO.transfer(id,amount,account_number);
			if (flag==0) {
				return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity(flag, HttpStatus.OK);
		}
		
}
	
	

