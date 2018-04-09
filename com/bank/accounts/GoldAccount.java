package com.bank.accounts;

import java.util.Date;

import com.bank.customer.Customer;

public class GoldAccount extends Account{
	
	private final double FIXED_INTEREST_RATE = .05;
	
	/**
	 * @param accountNumber
	 * @param accountBalance
	 * @param customer
	 * @param dateCreated
	 */
	public GoldAccount(long accountNumber, double accountBalance, Customer customer, Date dateCreated) {
		super(accountNumber, accountBalance, customer, dateCreated);
	}

	/**
	 * @return the fIXED_INTEREST_RATE
	 */
	public double getFIXED_INTEREST_RATE() {
		return FIXED_INTEREST_RATE;
	}

	/**
	 * Withdraw given amount from selected account. When the account balance is less than the selected amount, the selected amount will still be withdrawn, but the account will have a balance of 0
	 *@param amount 
	 */
	public void withdraw(double amount) {
		double balance = getAccountBalance();
		if(balance >= amount) {
			balance = balance - amount;
		}
		else if(balance < amount) {
			System.out.println("Your balance is lower than the amount requested, but since you have a Gold account you can withdraw that amount anyway");
			setAccountBalance(0);
		}
		transactions++;
	}
	
	/**
	 * Used by the applyAccountUpdates in the Bank class. Adds the interest to the balance, and sets the new balance
	 */
	public void calculateInterest() {;
		double balance = getAccountBalance();
		
		double newBalance = balance + (balance * FIXED_INTEREST_RATE);
		setAccountBalance(newBalance);
	}

	@Override
	public String toString() {
		return "GoldAccount [FIXED_INTEREST_RATE=" + FIXED_INTEREST_RATE + ", accountNumber=" + accountNumber
				+ ", accountBalance=" + accountBalance + ", customer=" + customer + ", dateCreated=" + dateCreated
				+ ", transactions=" + transactions + "]";
	}

}
