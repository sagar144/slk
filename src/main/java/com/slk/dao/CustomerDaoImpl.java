package com.slk.dao;
import com.slk.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.text.*;
import java.util.ArrayList;
import java.util.List;
import com.slk.util.*;
import java.sql.*;

import org.springframework.stereotype.Repository;

import com.slk.model.Customer;
import com.slk.model.Loan;
import com.slk.model.Transaction;
import java.util.Date;


@Repository
public class CustomerDaoImpl implements CustomerDAO{
	
	static List<Customer> list1=new ArrayList();
	static List<Transaction> trans=new ArrayList();
	//static List<Loan> li=new ArrayList();
	public CustomerDaoImpl() {
		try{
			Connection conn=DBUtil.getConnection();
			Statement stmt = conn.createStatement(); 
	        ResultSet rs = stmt.executeQuery("select * from customer"); 
	        while (rs.next()) { 
	         
	            Customer cust=new Customer();
	            cust.setAccount_no(rs.getInt("account_no"));
	            cust.setName(rs.getString("name"));
	            cust.setDob(rs.getString("DOB"));
	            cust.setPhone_no(rs.getInt("phone_no"));
	            cust.setUsername(rs.getString("username"));
	            cust.setPassword(rs.getString("password"));
	            cust.setAmount(rs.getDouble("amount"));
	            cust.setBranch_id(rs.getInt("branch_id"));
	            cust.setLoan_id(rs.getString("loan_id"));
	            cust.setType_id(rs.getString("type_id"));
	            list1.add(cust);
	            
	       
	        } 
	        rs.close(); 
	        stmt.close();
			
		}catch(Exception e){
			System.out.println(e);
		}}
	
	//view PROFILE
	@Override
	public Customer viewProfile(int id) {
		for (Customer c : list1) {
				if (c.getAccount_no()==id) {
					System.out.println(c.getAccount_no());
					System.out.println(c.getName());
					return c;
				}
			}
			return null;
		}
		//VIEW ALL CUSTOMERS
	@Override
	public List<Customer> viewAllCustomer() {
		// TODO Auto-generated method stub
		return list1;
}
	//TRANSFER
	

	public int transfer(int id,double amount,int account_number) {
		// TODO Auto-generated method stub
		int sendAccNo=id;
		int receiveAccNo=account_number;
		double sendTempAmt=0;
		double receiveTempAmt=0;
		double tempAmt=0;
		String accType="";
		double debit=0;
		double credit=0;
		int transId=0;
		int flag=0;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(formatter.format(date));
		CustomerDAO cust=new CustomerDaoImpl();
		try{
			Connection conn=DBUtil.getConnection();
			Statement stmt = conn.createStatement(); 
	        ResultSet rs = stmt.executeQuery("select amount,type_id from customer where account_no="+sendAccNo); 
	        while (rs.next()) { 
	        	sendTempAmt=rs.getDouble("amount");
	        	accType=rs.getString("type_id");
	        }
	       rs = stmt.executeQuery("select amount from customer where account_no="+receiveAccNo); 
	        while (rs.next()) { 
	        	receiveTempAmt=rs.getDouble("amount");
	        }
	        rs = stmt.executeQuery("select max(id) as id from transaction"); 
	        while (rs.next()) { 
	        	transId=rs.getInt("id");
	        }
	        transId++;
	        char c=accType.charAt(0);
	        if(c=='C'){
	        	tempAmt=sendTempAmt-amount;
	        	if(tempAmt>=5000){
	        		debit=amount;
	        		
	        		stmt.executeUpdate("update customer set amount="+tempAmt+"where account_no="+sendAccNo);
	        		stmt.executeUpdate("insert into transaction values("+transId+",'"+formatter.format(date)+"',"+credit+","+debit+","+sendAccNo+","+tempAmt+")");
	        		System.out.println("Sender balance= "+tempAmt);
	        		debit=0;
	        		credit=amount;
	        		
	        		tempAmt=receiveTempAmt+amount;
	        		transId++;
	        		stmt.executeUpdate("update customer set amount="+tempAmt+"where account_no="+receiveAccNo);
	        		stmt.executeUpdate("insert into transaction values("+transId+",'"+formatter.format(date)+"',"+credit+","+debit+","+receiveAccNo+","+tempAmt+")");
	        		System.out.println("receiver balance= "+tempAmt);
	        	}else{
	        		System.out.println("No Minimum Balance");
	        	}
	        }else{
	        	tempAmt=sendTempAmt-amount;
	        	if(tempAmt>=500){
	        		debit=amount;
	        		
	        		stmt.executeUpdate("update customer set amount="+tempAmt+"where account_no="+sendAccNo);
	        		stmt.executeUpdate("insert into transaction values("+transId+",'"+formatter.format(date)+"',"+credit+","+debit+","+sendAccNo+","+tempAmt+")");
	        		System.out.println("Sender balance= "+tempAmt);
	        		debit=0;
	        		credit=amount;
	        		
	        		tempAmt=receiveTempAmt+amount;
	        		transId++;
	        		stmt.executeUpdate("update customer set amount="+tempAmt+"where account_no="+receiveAccNo);
	        		stmt.executeUpdate("insert into transaction values("+transId+",'"+formatter.format(date)+"',"+credit+","+debit+","+receiveAccNo+","+tempAmt+")");
	        		System.out.println("receiver balance= "+tempAmt);
	        	}else{
	        		System.out.println("No Minimum Balance");
	        	}
	        }
	        
	        
	        	        
	        
		}catch(Exception e){
			System.out.println(e);
		}
		
		return 1;
	}   
	        
	        
	        	     


//TRANSACTION
@Override	
public List<Transaction> transactionhistory(int id) {
		// TODO Auto-generated method stub
		
		try{
			Transaction tl=new Transaction();
			Connection conn=DBUtil.getConnection();
			Statement stmt = conn.createStatement(); 
	        ResultSet rs = stmt.executeQuery("select * from transaction where account_no="+id); 
	        while (rs.next()) {
	        	tl.setId(rs.getInt("id"));
	        	tl.setTrans_date(rs.getString("trans_date"));
	        	tl.setCredit(rs.getDouble("credit"));
	        	tl.setDebit(rs.getDouble("debit"));
	        	tl.setAccount_no(rs.getInt("account_no"));
	        	tl.setBalance(rs.getInt("balance"));
	        	System.out.println(rs.getString("trans_date"));
	        	trans.add(tl);
	        }
		}catch(Exception e){
			System.out.println(e);
		}
			
				return trans;
	
		}
//BALANCE
public double viewBalance(int id) {
		// TODO Auto-generated method stub
		for (Customer c : list1) {
			System.out.println(c.getAccount_no());
			if (c.getAccount_no()==id) {
				return c.getAmount();
			}
		}
	return 0;
	}

//LOAN
	@Override
public Loan viewLoan(int id) {
		Loan ln=new Loan();
		for (Customer c:list1) {
			System.out.println(c.getAccount_no());
			if (c.getAccount_no()==id) {
		
				String ltype=c.getLoan_id();
				char tempChar=ltype.charAt(0);
				if(tempChar=='C'){
					System.out.println("car loan");
					try{
						Connection conn=DBUtil.getConnection();
						Statement stmt = conn.createStatement(); 
				        ResultSet rs = stmt.executeQuery("select loan.id as id,loan.loan_type as loan_type ,loan.interest_rate as interest_rate from customer,loan where account_no="+id+" and id='"+ltype+"'" );  
				        
				        while (rs.next()) { 
				        	ln.setId(rs.getString("id"));
				        	ln.setLoan_type(rs.getString("loan_type"));
				        	ln.setInterest_rate(rs.getDouble("interest_rate"));
				        	System.out.println(ln.getId());
				        } 
				        rs.close(); 
				        stmt.close();
						
					}catch(Exception e){
						System.out.println(e);
					}
				}else if(tempChar=='H'){
					System.out.println("Home loan");
					
					try{
						Connection conn=DBUtil.getConnection();
						Statement stmt = conn.createStatement(); 
						ResultSet rs = stmt.executeQuery("select loan.id as id,loan.loan_type as loan_type ,loan.interest_rate as interest_rate from customer,loan where account_no="+id+" and id='"+ltype+"'" );
				       
				        while (rs.next()) { 
				        	ln.setId(rs.getString("id"));
				        	ln.setLoan_type(rs.getString("loan_type"));
				        	ln.setInterest_rate(rs.getDouble("interest_rate"));
				        	System.out.println(ln.getId());
				        } 
				        rs.close(); 
				        stmt.close();
						
					}catch(Exception e){
						System.out.println(e);
					}
					
				}else if(tempChar=='S'){
					System.out.println("Student loan");
					
					try{
						Connection conn=DBUtil.getConnection();
						Statement stmt = conn.createStatement(); 
						ResultSet rs = stmt.executeQuery("select loan.id as id,loan.loan_type as loan_type ,loan.interest_rate as interest_rate from customer,loan where account_no="+id+" and id='"+ltype+"'" );
				        
				        while (rs.next()) { 
				        	ln.setId(rs.getString("id"));
				        	ln.setLoan_type(rs.getString("loan_type"));
				        	ln.setInterest_rate(rs.getDouble("interest_rate"));
				        	System.out.println(ln.getId());
				        } 
				        rs.close(); 
				        stmt.close();
						
					}catch(Exception e){
						System.out.println(e);
					}
										
				}else{
					System.out.println("No loan");
				}
				return ln;
			}
		}
		return null;
}

	
//UPDATE
public void updateprofile(int id,Customer cust){
		for (Customer c:list1) {
			if (c.getAccount_no()==id) {
				System.out.println("update inside");
				try{
					Connection conn=DBUtil.getConnection();
					Statement stmt = conn.createStatement(); 
			        stmt.executeUpdate("update customer set name='"+cust.getName()+"',DOB='"+cust.getDob()+"',phone_no="+cust.getPhone_no()+",username='"+cust.getUsername()+"',password='"+cust.getPassword()+"' where account_no="+id+""); 
			        System.out.println("successfull updated......!");
				}catch(Exception e){
					System.out.println(e);
				}
				
			}
		}
		
	}
	
	public List list() {
		// TODO Auto-generated method stub
		return list1;
	}

	
	
	
}