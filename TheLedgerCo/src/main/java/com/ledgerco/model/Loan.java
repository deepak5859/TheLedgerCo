package com.ledgerco.model;

public class Loan {
	String bankName;
	String borrowerName;
	int principalAmount;
	int noOfYears;
	int rateOfInterest;
	
	public Loan() {
	}
	
	public Loan(String bankName, String borrowerName, int principalAmount, int noOfYears, int rateOfInterest) {
		super();
		this.bankName = bankName;
		this.borrowerName = borrowerName;
		this.principalAmount = principalAmount;
		this.noOfYears = noOfYears;
		this.rateOfInterest = rateOfInterest;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public int getPrincipalAmount() {
		return principalAmount;
	}

	public void setPrincipalAmount(int principalAmount) {
		this.principalAmount = principalAmount;
	}

	public int getNoOfYears() {
		return noOfYears;
	}

	public void setNoOfYears(int noOfYears) {
		this.noOfYears = noOfYears;
	}

	public int getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(int rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	@Override
	public String toString() {
		return "Loan [bankName=" + bankName + ", borrowerName=" + borrowerName + ", principalAmount=" + principalAmount
				+ ", noOfYears=" + noOfYears + ", rateOfInterest=" + rateOfInterest + "]";
	}
	
}
