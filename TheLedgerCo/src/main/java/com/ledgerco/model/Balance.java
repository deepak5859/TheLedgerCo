package com.ledgerco.model;

public class Balance {
	String bankName;
	String borrowerName;
	int emiNo;
	
	public Balance() {
	}
	
	public Balance(String bankName, String borrowerName, int emiNo) {
		this.bankName = bankName;
		this.borrowerName = borrowerName;
		this.emiNo = emiNo;
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

	public int getEmiNo() {
		return emiNo;
	}

	public void setEmiNo(int emiNo) {
		this.emiNo = emiNo;
	}

	@Override
	public String toString() {
		return "Balance [bankName=" + bankName + ", borrowerName=" + borrowerName + ", emiNo=" + emiNo + "]";
	}
	
}
