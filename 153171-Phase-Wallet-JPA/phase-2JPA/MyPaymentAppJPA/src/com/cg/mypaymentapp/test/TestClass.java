package com.cg.mypaymentapp.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Test;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;

public class TestClass {

	WalletService service = new WalletServiceImpl();

	@Test(expected = NullPointerException.class)
	public void testCreateAccount() {
		service.createAccount(null, null, null);
	}

	@Test
	public void testCreateAccount2() {
		Customer cust = new Customer();
		cust = service.createAccount("abcd", "8019413949", new BigDecimal(
				7000));
		assertEquals("adcb", cust.getName());
	}

	@Test
	public void testCreateAccount3() {
		Customer cust = new Customer();
		cust = service.createAccount("lmno", "9900112213", new BigDecimal(7000));
		assertEquals("9900112213", cust.getMobileNo());
	}

	@Test(expected = InvalidInputException.class)
	public void testCreateAccount4() {
		service.createAccount("abcd", "8019413948", new BigDecimal("092"));
	}

	@Test
	public void testShowBalance() {
		Customer customer = service.showBalance("9963242422");
		assertEquals(new BigDecimal("9000.00"), customer.getWallet()
				.getBalance());
	}

	@Test(expected = InvalidInputException.class)
	public void testShowBalance2() {
		service.showBalance("9579405744");
	}

	@Test
	public void testShowBalance3() {
		Customer cust = new Customer();
		cust = service.showBalance("9922950519");
		assertEquals(new BigDecimal("7000.00"), cust.getWallet().getBalance());
	}

	@Test
	public void testShowBalance4() {
		Customer cust = new Customer();
		cust = service.showBalance("9900112212");
		BigDecimal actual = cust.getWallet().getBalance();
		BigDecimal expected = new BigDecimal("6000.00");
		assertEquals(expected, actual);
	}

	@Test
	public void testFundTransfer() {
		Customer c = service.fundTransfer("9900112212", "9963242422",
				new BigDecimal(3000));
		assertEquals(new BigDecimal("6000.00"), c.getWallet().getBalance());
	}

	@Test(expected = InvalidInputException.class)
	public void testFundTransfer2() {
		service.fundTransfer("9874563210", "9874563210", new BigDecimal(
				"7000.00"));
	}

	@Test
	public void testFundTransfer3() {
		service.fundTransfer("9900112212", "9963242422", new BigDecimal(2000));
		BigDecimal actual = service.showBalance("9900112212").getWallet()
				.getBalance();
		BigDecimal expected = new BigDecimal("4000.00");
		assertEquals(expected, actual);
	}

	@Test(expected = InsufficientBalanceException.class)
	public void testFundTransfer4() {
		service.fundTransfer("9900112212", "9963242422", new BigDecimal(9000));
	}

	@Test(expected = InsufficientBalanceException.class)
	public void testFundTransfer5() {
		service.fundTransfer("9900112212", "9963242422", new BigDecimal(9000));
	}

	@Test(expected = InsufficientBalanceException.class)
	public void testFundTransfer6() {
		service.fundTransfer("9900112212", "9963242422", new BigDecimal(9000));
	}

	@Test(expected = InvalidInputException.class)
	public void testFundTransfer7() {
		service.fundTransfer("9900112212", "801941399", new BigDecimal("092"));
	}

	@Test
	public void testDeposit() {
		Customer customer = service.depositAmount("9963242422", new BigDecimal(
				3000));
		assertEquals(new BigDecimal("11000.00"), customer.getWallet()
				.getBalance());
	}

	@Test(expected = InvalidInputException.class)
	public void testDeposit2() {
		service.depositAmount("900000000", new BigDecimal(2000));
	}

	@Test
	public void testDeposit3() {
		Customer cust1 = service.depositAmount("9963242422", new BigDecimal(
				2000));
		BigDecimal actual = cust1.getWallet().getBalance();
		BigDecimal expected = new BigDecimal("11000.00");
		assertEquals(expected, actual);
	}

	@Test
	public void testWithdraw() {
		Customer customer = service.withdrawAmount("9963242422",
				new BigDecimal(3000));
		assertEquals(new BigDecimal("6000.00"), customer.getWallet()
				.getBalance());
	}

	@Test(expected = InvalidInputException.class)
	public void testWithdraw2() {
		service.withdrawAmount("900000000", new BigDecimal(2000));
	}

	@Test
	public void testWithdraw3() {
		service.withdrawAmount("9963242422", new BigDecimal(2000));
		BigDecimal actual = service.showBalance("9900112212").getWallet()
				.getBalance();
		BigDecimal expected = new BigDecimal("4000.00");
		assertEquals(expected, actual);
	}

	@Test(expected = InsufficientBalanceException.class)
	public void testWithdraw4() {
		service.withdrawAmount("9963242422", new BigDecimal(60000));
	}

	@Test(expected = InsufficientBalanceException.class)
	public void testWithdraw5() {
		service.withdrawAmount("9900112212", new BigDecimal(9000));
	}

	@Test(expected = InvalidInputException.class)
	public void testName() {
		service.createAccount("lmno", "9900112213", new BigDecimal(7000));
	}

	@Test(expected = InvalidInputException.class)
	public void testMobileNumber() {
		service.createAccount("lmno", "990011221", new BigDecimal(7000));
	}

	@After
	public void testAfter() {
		service = null;
	}
}
