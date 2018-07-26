package com.cg.mypaymentapp.pl;

import java.math.BigDecimal;
import java.util.Scanner;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;

public class Client {
	private WalletService service;
	Scanner sc = new Scanner(System.in);

	public Client() {
		service = new WalletServiceImpl();
	}

	void menu() {
		int choice = 0;
		System.out.println("1) Create an account");
		System.out.println("2) Show current balance");
		System.out.println("3) Transfer funds to another account");
		System.out.println("4) Deposit to account");
		System.out.println("5) Withdraw from account");
		System.out.println("6) Exit");
		System.out.println("Enter your choice");
		choice = sc.nextInt();
		String mobileNumber = null;
		switch (choice) {
		case 1:
			System.out.println("Enter name");
			String name = sc.next();
			System.out.println("Enter mobile number");
			mobileNumber = sc.next();
			System.out.println("Enter the amount");
			BigDecimal amount = sc.nextBigDecimal();
			Customer customer = service.createAccount(name, mobileNumber, amount);
			if (customer != null) {
				System.out.println("Account successfully created. Details: " + customer.getName() + " "
						+ customer.getMobileNo() + " " + customer.getWallet().getBalance());
			} else {
				System.out.println("Account not created");
			}
			break;
		case 2:
			System.out.println("Enter mobile number");
			mobileNumber = sc.next();
			customer = service.showBalance(mobileNumber);
			System.out.println("Current balance: " + customer.getWallet().getBalance());
			break;
		case 3:
			System.out.println("Enter mobile number");
			mobileNumber = sc.next();
			System.out.println("Enter target mobile number");
			String targetMobileNo = sc.next();
			System.out.println("Enter amount to transfer");
			BigDecimal amountToTransfer = sc.nextBigDecimal();
			customer = service.fundTransfer(mobileNumber, targetMobileNo, amountToTransfer);
			if (customer != null) {
				System.out.println("Transferred " + amountToTransfer + " to " + targetMobileNo + ".Updated balance is "
						+ customer.getWallet().getBalance());
			} else {
				System.out.println("Denied");
			}
			break;
		case 4:
			System.out.println("Enter mobile number");
			mobileNumber = sc.next();
			System.out.println("Enter amount to deposit");
			BigDecimal depositAmount = sc.nextBigDecimal();
			customer = service.depositAmount(mobileNumber, depositAmount);
			if (customer != null) {
				System.out.println("Deposited successfully. Updated balance is " + customer.getWallet().getBalance());
			} else {
				System.out.println("Denied.");
			}
			break;
		case 5:
			System.out.println("Enter mobile number");
			mobileNumber = sc.next();
			System.out.println("Enter amount to withdraw");
			BigDecimal withdrawAmount = sc.nextBigDecimal();
			customer = service.withdrawAmount(mobileNumber, withdrawAmount);
			if (customer != null) {
				System.out.println("Withdrawn successfully. Updated balance is " + customer.getWallet().getBalance());
			} else {
				System.out.println("Denied");
			}
			break;
		case 6:
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		Client client = new Client();
		while (true)
			client.menu();
	}
}
