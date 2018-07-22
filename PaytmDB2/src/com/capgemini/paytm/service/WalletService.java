package com.capgemini.paytm.service;

import java.math.BigDecimal;
import java.util.List;

import com.capgemini.paytm.beans.Customer;
import com.capgemini.paytm.beans.Transactions;
import com.capgemini.paytm.exceptions.InsufficientBalanceException;
import com.capgemini.paytm.exceptions.InvalidInputException;

public interface WalletService {
	public Customer createAccount(String name ,String mobileno, BigDecimal amount) throws InvalidInputException;
	public Customer showBalance (String mobileno) throws InvalidInputException;
	public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, BigDecimal amount) throws InvalidInputException, InsufficientBalanceException;
	public Customer depositAmount (String mobileNo,BigDecimal amount ) throws InvalidInputException;
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws InvalidInputException, InsufficientBalanceException;
	public List<Transactions> getAllTransactions(String mobileNumber);

}
