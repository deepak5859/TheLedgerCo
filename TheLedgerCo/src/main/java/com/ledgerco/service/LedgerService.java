package com.ledgerco.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import com.ledgerco.model.Balance;
import com.ledgerco.model.Loan;
import com.ledgerco.model.Payment;
import com.ledgerco.util.FileUtil;

public class LedgerService implements ILedgerService {
	private static final String ACTION_LOAN = "LOAN";
	private static final String ACTION_PAYMENT = "PAYMENT";
	private static final String ACTION_BALANCE = "BALANCE";
	
	private static final int ROI_PERCENTAGE = 100;
	private static final int NO_OF_MONTHS_IN_YEAR = 12;
	private static final int MINIMUM_LINE_TOKENS = 4;
	
	private static final String UNDERSCORE_SYMBOL = "_";
	private static final String BLANC_SPACE= " ";
	
	
	private Map<String, String> inputCommandMap = new HashMap<>();
	private List<String> cmdOutputList = new ArrayList<>();
	
	public List<String> processInputFile(List<String> listOfLines) {
		listOfLines.forEach(line -> processLine(line));
		
		return cmdOutputList;
	}
	
	private void processLine(String lineCmd) {
		String[] splitLineCmd = lineCmd.split(BLANC_SPACE);
		
		if(splitLineCmd.length >= MINIMUM_LINE_TOKENS) {
			int index = 0;
			String command = splitLineCmd[index++];
			
			switch (command) {
				case ACTION_LOAN: {
					processLoan(new Loan(splitLineCmd[index++], splitLineCmd[index++], Integer.valueOf(splitLineCmd[index++]), 
							Integer.valueOf(splitLineCmd[index++]), Integer.valueOf(splitLineCmd[index++])));
					break;
				}
				
				case ACTION_PAYMENT: {
					processPayment(new Payment(splitLineCmd[index++], splitLineCmd[index++], Integer.valueOf(splitLineCmd[index++]), Integer.valueOf(splitLineCmd[index++])));
					break;
				}
				
				case ACTION_BALANCE: {
					String balanceCMDOutput = processBalance(new Balance(splitLineCmd[index++], splitLineCmd[index++], Integer.valueOf(splitLineCmd[index++])));
					this.cmdOutputList.add(balanceCMDOutput);
					break;
				}
	
				default: break;
			}
		}
	}
	
	public void processLoan(Loan loan) {
		StringJoiner strJoinerKey = new StringJoiner(UNDERSCORE_SYMBOL);
		strJoinerKey.add(ACTION_LOAN);
		strJoinerKey.add(loan.getBankName());
		strJoinerKey.add(loan.getBorrowerName());
		
		StringJoiner strJoinerValue = new StringJoiner(UNDERSCORE_SYMBOL);
		strJoinerValue.add(String.valueOf(loan.getPrincipalAmount()));
		strJoinerValue.add(String.valueOf(loan.getNoOfYears()));
		strJoinerValue.add(String.valueOf(loan.getRateOfInterest()));
		
		inputCommandMap.put(strJoinerKey.toString(), strJoinerValue.toString());
	}

	public void processPayment(Payment payment) {
		StringJoiner strJoinerKey = new StringJoiner(UNDERSCORE_SYMBOL);
		strJoinerKey.add(ACTION_PAYMENT);
		strJoinerKey.add(payment.getBankName());
		strJoinerKey.add(payment.getBorrowerName());
		
		StringJoiner strJoinerValue = new StringJoiner(UNDERSCORE_SYMBOL);
		strJoinerValue.add(String.valueOf(payment.getLumpSumAmount()));
		strJoinerValue.add(String.valueOf(payment.getEmiNo()));
		
		inputCommandMap.put(strJoinerKey.toString(), strJoinerValue.toString());	
	}

	public String processBalance(Balance balance) {
		int index = 0;
		
		StringJoiner strJoinerKey = new StringJoiner(UNDERSCORE_SYMBOL);
		strJoinerKey.add(ACTION_LOAN);
		strJoinerKey.add(balance.getBankName());
		strJoinerKey.add(balance.getBorrowerName());
		
		String existingLoanStr = inputCommandMap.get(strJoinerKey.toString());
		String[] existingLoanArr = existingLoanStr.split(UNDERSCORE_SYMBOL);
		
		int principle = Integer.valueOf(existingLoanArr[index++]);
		int noOfYears = Integer.valueOf(existingLoanArr[index++]);
		int rateOfInterest = Integer.valueOf(existingLoanArr[index++]);
		
		int totalLoanInterest = (int) Math.ceil(((double) principle * noOfYears * rateOfInterest) / ROI_PERCENTAGE);
		int totalAmountRepay  = principle + totalLoanInterest;
		int monthlyEMI = (int) (Math.ceil(totalAmountRepay / (double) (noOfYears * NO_OF_MONTHS_IN_YEAR)));
		int emiNo = balance.getEmiNo();
		
		StringJoiner strJoinerKeyLumpsum = new StringJoiner(UNDERSCORE_SYMBOL);
		strJoinerKeyLumpsum.add(ACTION_PAYMENT);
		strJoinerKeyLumpsum.add(balance.getBankName());
		strJoinerKeyLumpsum.add(balance.getBorrowerName());
		
		String existingLumpSumPaymentStr = inputCommandMap.get(strJoinerKeyLumpsum.toString());
		
		int lumpSumPayment = 0;
		int lumpSumPaymentEMINo = 0;
		
		if(existingLumpSumPaymentStr != null) {
			String[] existingLumpSumPaymentArr = existingLumpSumPaymentStr.split(UNDERSCORE_SYMBOL);
		
			lumpSumPayment  = Integer.valueOf(existingLumpSumPaymentArr[0]);
			lumpSumPaymentEMINo  = Integer.valueOf(existingLumpSumPaymentArr[1]);
		}
		
		int emiPaidTillNow = 0;
		int remainingEMIMonths = 0;
		
		if(lumpSumPayment == 0 || emiNo < lumpSumPaymentEMINo) {
			emiPaidTillNow = (int) Math.ceil((double) balance.getEmiNo() * monthlyEMI);
			remainingEMIMonths = (int) Math.ceil((double)(noOfYears * NO_OF_MONTHS_IN_YEAR) - emiNo);
		} else if(lumpSumPayment > 0 && emiNo >= lumpSumPaymentEMINo) {
			emiPaidTillNow = (int) Math.ceil((double)(balance.getEmiNo() * monthlyEMI) + lumpSumPayment);
			remainingEMIMonths = (int) Math.ceil((double)(totalAmountRepay - emiPaidTillNow) / monthlyEMI);
		}
		
		strJoinerKey = new StringJoiner(BLANC_SPACE);
		strJoinerKey.add(balance.getBankName());
		strJoinerKey.add(balance.getBorrowerName());
		strJoinerKey.add(String.valueOf(emiPaidTillNow));
		strJoinerKey.add(String.valueOf(remainingEMIMonths));
		
		return strJoinerKey.toString();
	}

}
