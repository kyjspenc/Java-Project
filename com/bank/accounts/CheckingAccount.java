package com.bank.accounts;

import java.util.Date;

import com.bank.customer.Customer;

public class CheckingAccount extends Account {
	
	private final double MINIMUM_BALANCE = 100;
	private final double TRANSACTION_FEE = 3;

	/**
	 * Constructor to make a checking account
	 * @param accountNumber
	 * @param accountBalance
	 * @param customer
	 * @param dateCreated
	 * @param transactions
	 * @param minimumBalance
	 */
	public CheckingAccount(long accountNumber, double accountBalance, Customer customer, Date dateCreated) {
		super(accountNumber, accountBalance, customer, dateCreated);
	}

	public double getMinimumBalance() {
		return MINIMUM_BALANCE;
	}

	public double getTRANSACTION_FEE() {
		return TRANSACTION_FEE;
	}
	/**
	 * Method is used by the applyAccountUpdates in the Bank class to deduct monthly fees for the transactions of the account
	 */
	public void deductFees() {
		int transactions = getTransactions();
		if(transactions < 2) {
			double monthlyFees = transactions * TRANSACTION_FEE;
			double balance = getAccountBalance();
			balance = balance - monthlyFees;
			setAccountBalance(balance);
		}
	}
	/**
	 * Withdraws given amount to selected account. Displays message if account balance is lower than amount requested, and withdraws current balance if too low..
	 * @param amount
	 */
	public void withdraw(double amount) {
		double balance = getAccountBalance();
		if(balance >= amount) {
			balance = balance - amount;
		}
		else if(balance < amount) {
			System.out.println("Your balance is too low.\n" + balance + " is withdrawn instead");
			setAccountBalance(0);
		}
		transactions++;
	}

	@Override
	public String toString() {
		return "CheckingAccount [accountNumber = " + accountNumber + ", accountBalance = " + accountBalance + ", customer = "
				+ customer + ", dateCreated = " + dateCreated + ", transactions = " + transactions + "]";
	}
}
