package com.capgemini.paytm.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.*;

import com.capgemini.paytm.beans.Customer;
import com.capgemini.paytm.beans.Wallet;
import com.capgemini.paytm.exceptions.InsufficientBalanceException;
import com.capgemini.paytm.exceptions.InvalidInputException;
import com.capgemini.paytm.service.WalletService;
import com.capgemini.paytm.service.WalletServiceImpl;

public class TestCases {

	static WalletService walletService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		walletService = new WalletServiceImpl();
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testshowBalance() throws InvalidInputException {
		String number = "9177640926";
		Customer customer = walletService.showBalance(number);
		assertNotEquals(null,customer);
	}
	
	@Test(expected = InvalidInputException.class)
	public void testMobileNumber() throws InvalidInputException, InsufficientBalanceException {
		String name = "Anvitha";
		String mobileNumber = "91776409266";
		BigDecimal balance = new BigDecimal(3000);
		
		walletService.createAccount(name, mobileNumber, balance);
		
		BigDecimal amount = new BigDecimal(3000);
		
		walletService.withdrawAmount(mobileNumber, amount);
	}
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount1() throws InvalidInputException 
	{
		walletService.createAccount(null, "9177640926", new BigDecimal(2000));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount2() throws InvalidInputException 
	{
		walletService.createAccount("", "9177640926", new BigDecimal(2000));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount3() throws InvalidInputException 
	{
		walletService.createAccount("allj", "718", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount4() throws InvalidInputException 
	{
		walletService.createAccount("anvitha", "", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount5() throws InvalidInputException 
	{
		walletService.createAccount("", "", new BigDecimal(1500));
	}
	
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount7() throws InvalidInputException 
	{
		walletService.createAccount("alekhya", "95500456", new BigDecimal(9000));
		walletService.createAccount("anvitha", "955004566", new BigDecimal(10000));
	}
	@Test(expected=InvalidInputException.class)
	public void testWithdraw() throws InvalidInputException, InsufficientBalanceException {
		String mobileNumber = "95500466";
		BigDecimal amount = new BigDecimal(3000);
		walletService.withdrawAmount(mobileNumber, amount);
	}
	@Test(expected=NullPointerException.class)
	public void testWithdraw1() throws InvalidInputException, InsufficientBalanceException
	{
		walletService.withdrawAmount(null, null);
	}
	@Test(expected=InvalidInputException.class)
	public void testWithdraw2() throws InvalidInputException, InsufficientBalanceException
	{
		walletService.withdrawAmount("900000000", new BigDecimal(2000));
	}
		
	@Test(expected=NullPointerException.class)
	public void testWithdraw4() throws InvalidInputException, InsufficientBalanceException
	{
		walletService.withdrawAmount(null, new BigDecimal(2000));
	}
	@Test(expected=InvalidInputException.class)
	public void testWithdraw5() throws InvalidInputException, InsufficientBalanceException {
		String mobileNumber = "95500466";
		BigDecimal amount = new BigDecimal(-300);
		walletService.withdrawAmount(mobileNumber, amount);
	}
	@Test(expected=InvalidInputException.class)
	public void testDeposit() throws InvalidInputException,InsufficientBalanceException {
		
		String mobileNumber = "95500456";
		Customer customer1 = walletService.depositAmount(mobileNumber, new BigDecimal(3000));
		System.out.println(customer1.getWallet().getBalance());
		assertEquals(8000 , customer1.getWallet().getBalance());
	}
	@Test(expected=InvalidInputException.class)
	public void testDeposit1() throws InvalidInputException
	{
		walletService.depositAmount("900000000", new BigDecimal(2000));
	}
		
	@Test(expected=InvalidInputException.class)
	public void testDeposit2() throws InvalidInputException,InsufficientBalanceException
	{
		Customer customer=walletService.depositAmount("9963242422", new BigDecimal(2000));
		BigDecimal actual=customer.getWallet().getBalance();
		BigDecimal expected=new BigDecimal(8000);
		assertEquals(expected, actual);
	}
	
	@Test(expected=NullPointerException.class)
	public void testDeposit3() throws InvalidInputException
	{
		walletService.depositAmount(null, new BigDecimal(2000));
	}
	@Test(expected=NullPointerException.class)
	public void testDeposit4() throws InvalidInputException
	{
		String mobileNumber="9177640926";
		walletService.depositAmount(mobileNumber, null);
	}
	@Test(expected=NullPointerException.class)
	public void testDeposit5() throws InvalidInputException
	{
		walletService.depositAmount(null, null);
	}
	
	@Test(expected=InvalidInputException.class)
	public void testDeposit6() throws InvalidInputException
	{
		walletService.depositAmount("900000000", new BigDecimal(-2000));
	}
}
