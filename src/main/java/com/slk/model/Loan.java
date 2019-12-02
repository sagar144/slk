package com.slk.model;
import java.io.Serializable;
import java.util.Date;


public class Loan implements Serializable {
		private String id;
		private String loan_type;
		private String discription;
		private String eligibility;
		private double interest_rate;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getLoan_type() {
			return loan_type;
		}
		public void setLoan_type(String loan_type) {
			this.loan_type = loan_type;
		}
		public String getDiscription() {
			return discription;
		}
		public void setDiscription(String discription) {
			this.discription = discription;
		}
		public String getEligibility() {
			return eligibility;
		}
		public void setEligibility(String eligibility) {
			this.eligibility = eligibility;
		}
		public double getInterest_rate() {
			return interest_rate;
		}
		public void setInterest_rate(double interest_rate) {
			this.interest_rate = interest_rate;
		}
	 
	}


