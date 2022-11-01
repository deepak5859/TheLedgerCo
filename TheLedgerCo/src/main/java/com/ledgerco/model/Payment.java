package com.ledgerco.model;

public class Payment {
	String bankName;
	String borrowerName;
	int lumpSumAmount;
	int emiNo;
	
	public Payment() {
	}
	
	public Payment(String bankName, String borrowerName, int lumpSumAmount, int emiNo) {
		this.bankName = bankName;
		this.borrowerName = borrowerName;
		this.lumpSumAmount = lumpSumAmount;
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
	
	public int getLumpSumAmount() {
		return lumpSumAmount;
	}
	
	public void setLumpSumAmount(int lumpSumAmount) {
		this.lumpSumAmount = lumpSumAmount;
	}
	
	public int getEmiNo() {
		return emiNo;
	}
	
	public void setEmiNo(int emiNo) {
		this.emiNo = emiNo;
	}

	@Override
	public String toString() {
		return "Payment [bankName=" + bankName + ", borrowerName=" + borrowerName + ", lumpSumAmount=" + lumpSumAmount
				+ ", emiNo=" + emiNo + "]";
	}
	
}
