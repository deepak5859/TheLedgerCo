package com.ledgerco.service;

import com.ledgerco.model.Balance;
import com.ledgerco.model.Loan;
import com.ledgerco.model.Payment;

public interface ILedgerService {
	void processLoan(Loan loan);
	void processPayment(Payment payment);
	String processBalance(Balance balance);
}
