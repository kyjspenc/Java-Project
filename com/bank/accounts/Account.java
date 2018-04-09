package com.bank.accounts;

import java.util.Date;

import com.bank.customer.Customer;

public abstract class Account {
	protected long accountNumber;
	protected double accountBalance;
	protected Customer customer;
	protected Date dateCreated;
	protected int transactions = 0;
	
	
	/**
	 * Constructor for abstract account
	 * @param accountNumber
	 * @param accountBalance
	 * @param customer
	 * @param dateCreated
	 * @param transactions
	 */
	public Account(long accountNumber, double accountBalance, Customer customer, Date dateCreated) {
	
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.customer = customer;
		this.dateCreated = dateCreated;
		
	}

	public long getAccountNumber() {
		return accountNumber;
	}
	
	// Set methods have been removed from the classes. Account Numbers can not be changed once created.
	
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	// Set method has been removed from customer. Accounts cannot be transferred, therefore there should be no set method.
	
	public Date getDateCreated() {
		return dateCreated;
	}
	
	// Set method has been removed from dateCreated. That date cannot be changed.	
	
	
	public int getTransactions() {
		return transactions;
	}
	public void setTransactions(int transactions) {
		this.transactions = transactions;
	}
	/**
	 * Deposits given amount into selected account
	 * @param amount
	 */
	public void deposit(double amount) {
		double balance = getAccountBalance();
		balance = balance + amount;
		setAccountBalance(balance);
		transactions++;
		System.out.println("You have deposited $" + amount + " into your account.");
	}
	/**
	 * Abstract method to withdraw money from selected account for the given amount
	 * @param amount
	 */
	public abstract void withdraw(double amount);

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", accountBalance=" + accountBalance + ", customer="
				+ customer + ", dateCreated=" + dateCreated + ", transactions=" + transactions + "]";
	} 
	
}
