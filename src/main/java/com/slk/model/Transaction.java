package com.slk.model;
import java.io.Serializable;
import java.util.Date;


public class Transaction implements Serializable {
	
	private int id;
	private String trans_date;
	private double credit;
	private double debit;
	private int account_no;
	private double balance;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTrans_date() {
		return trans_date;
	}
	public void setTrans_date(String trans_date) {
		this.trans_date = trans_date;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double d) {
		this.credit = d;
	}
	public double getDebit() {
		return debit;
	}
	public void setDebit(double debit) {
		this.debit = debit;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getAccount_no() {
		return account_no;
	}
	public void setAccount_no(int account_no) {
		this.account_no = account_no;
	}

	}


