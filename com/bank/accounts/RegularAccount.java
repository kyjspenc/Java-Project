package com.bank.accounts;

import java.util.Date;

import com.bank.customer.Customer;

public class RegularAccount extends Account{
	private final double FIXED_INTEREST_RATE = .06;
	private final double FIXED_CHARGE = 10;
	

	/**
	 * @param accountNumber
	 * @param accountBalance
	 * @param customer
	 * @param dateCreated
	 */
	public RegularAccount(long accountNumber, double accountBalance, Customer customer, Date dateCreated) {
		super(accountNumber, accountBalance, customer, dateCreated);
	}
	
	
	/**
	 * @return the fIXED_INTEREST_RATE
	 */
	public double getFIXED_INTEREST_RATE() {
		return FIXED_INTEREST_RATE;
	}


	public double getFIXED_CHARGE() {
		return FIXED_CHARGE;
	}
	
	/**
	 * Calculates interest from the applyAccountUpdates method in the Bank class. Adds the interest accrued, and deducts the fixed charge for regular accounts
	 */
	public void calculateInterest() {
		double balance = getAccountBalance();
		double newBalance = balance + (balance * FIXED_INTEREST_RATE) - FIXED_CHARGE;
		setAccountBalance(newBalance);
	}

	/**
	 * Withdraws given amount from selected account. 
	 * @param amount
	 */
	public void withdraw(double amount) {
		double balance = getAccountBalance();
		if(balance >= amount) {
			balance = balance - amount;
		}else if(balance < amount) {
			System.out.println("Your balance is too low.\n" + balance + " is withdrawn instead");
			setAccountBalance(0);
		}
		transactions++;
	}
	
	@Override
	public String toString() {
		return "RegularAccount [FIXED_INTEREST_RATE=" + FIXED_INTEREST_RATE + ", FIXED_CHARGE=" + FIXED_CHARGE
				+ ", accountNumber=" + accountNumber + ", accountBalance=" + accountBalance + ", customer=" + customer
				+ ", dateCreated=" + dateCreated + ", transactions=" + transactions + "]";
	}
	
}
