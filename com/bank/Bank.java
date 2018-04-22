package com.bank;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.bank.accounts.*;
import com.bank.customer.*;
import com.bank.utils.UniqueIDFactory;

public class Bank implements Serializable {
	Scanner userInput = new Scanner(System.in);
	String input = "";
	String customerInput = "";
	long accountNumberInput = 0;
	
	ArrayList<Account> accountArray = new ArrayList<Account>();
	ArrayList<Customer> customerArray = new ArrayList<Customer>();
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		Bank b = new Bank();
		b.start();
	}

	public void start() throws ClassNotFoundException {
			
		openFileForReading();
		
		do {
			displayMenu();
			System.out.print("Please select an option: ");
			input = userInput.next();
			switch(input) {
				case "1": System.out.println("You have chosen to create a new Customer "); createCustomer(); break;
				case "2": System.out.println("You have chosen to create a new Checking account "); createCheckingAccount(); break;
				case "3": System.out.println("You have chosen to create a new Gold account "); createGoldAccount(); break;
				case "4": System.out.println("You have chosen to create a new Regular account "); createRegularAccount(); break;
				case "5": System.out.println("You have chosen to deposit into your account "); depositIntoAccount(); break;
				case "6": System.out.println("You have chosen to withdraw from your account "); withdrawFromAccount(); break;
				case "7": System.out.println("You have chosen to display information for an account "); displayAccountInfo(); break;
				case "8": System.out.println("You have chosen to remove an account "); removeAccount(); break;
				case "9": System.out.println("You have chosen to apply account updates "); applyAccountUpdates(); break;
				case "10": System.out.println("You have chosen to display Bank statistics "); displayBankStatistics(); break;
				case "11": System.out.println("You have chosen to display all accounts."); displayAllAccounts(); break;
				case "12": System.out.println("You have chosen to exit the application. \n\nThank you for choosing Bank Co to service your banking needs!"); exit(); break;
				default: System.out.println("....You have entered an invalid menu option....Please select a valid menu option\n");
			}
		}while (!input.equals("12"));
	}
	/**
	 * Display the menu of options for the teller
	 */
	public void displayMenu() {
		System.out.println("---Bank Teller Menu Options---");
		//System.out.print("////////////////////////////////////");
		System.out.println("\n1) \tcreate customer"); 
		System.out.println("2) \tcreate new Checking Account");
		System.out.println("3) \tcreate new Gold Account");
		System.out.println("4) \tcreate new Regular Account");
		System.out.println("5) \tdeposit into account");
		System.out.println("6) \twithdraw from account");
		System.out.println("7) \tdisplay account information");
		System.out.println("8) \tremove account");
		System.out.println("9) \tapply account updates");
		System.out.println("10) \tdisplay Bank statistics");
		System.out.println("11) \tdisplay all accounts");
		System.out.println("12) \texit");
		
		
	}
	
	public void createCustomer() {
		
		Customer newPersonal = null;
		Customer newCommercial = null;
		customerInput = "";
		String customerName;
		String customerFullName;
		String customerEmail;
		
		while(!customerInput.equals("1") && !customerInput.equals("2")) {
			System.out.print("\nIs this account for a personal customer(1) or a commercial customer(2): ");
			customerInput = userInput.next();
		}
		
		if (customerInput.equals("1")) {
			
			String customerHomePhone;
			String customerWorkPhone;
			
			// create Personal customer
			try {
				System.out.print("Please input the customer's first name: ");
				customerName = userInput.next();
				customerFullName = customerName;
				System.out.print("Please input the customer's surname: ");
				customerName = userInput.next();
				customerFullName += " " + customerName;
				System.out.print("\nPlease input a valid email: ");
				customerEmail = userInput.next();
				System.out.print("\nPlease input the customer's home phone number or a cell phone number: ");
				customerHomePhone = userInput.next();
				System.out.print("\nPlease input the customer's work phone number: ");
				customerWorkPhone = userInput.next();
			
			//PersonalCustomer(long customerId, String name, String email, String homePhone, String workPhone)
			newPersonal = new PersonalCustomer(UniqueIDFactory.generateUniqueID(), customerFullName, customerEmail, customerHomePhone, customerWorkPhone);
			customerArray.add(newPersonal);
			}catch (RuntimeException e ) {
				System.err.print("Invalid input");
			}
		} else if(customerInput.equals("2")) {
			
			int customerCreditRating;
			String contactPersonFirstName;
			String contactPersonLastName;
			String contactPerson;
			String contactPersonPhone;
			
			// create commercial customer
			try {
				System.out.print("Please input the customer's first name and surname: ");
				customerName = userInput.next();
				customerFullName = customerName;
				customerName = userInput.next();
				customerFullName += " " + customerName;
				System.out.print("\nPlease input a valid email: ");
				customerEmail = userInput.next();
				System.out.print("Please input the customer's credit rating: ");
				customerCreditRating = userInput.nextInt();
				System.out.print("Please input the first and last name of the contact person for the account: ");
				contactPersonFirstName = userInput.next();
				contactPersonLastName = userInput.next();
				contactPerson = contactPersonFirstName + contactPersonLastName;
				System.out.print("Please input the phone number for the contact person: ");
				contactPersonPhone = userInput.next();
				
				//CommercialCustomer(long customerId, String name, String email, int creditRating, String contactPerson,String contactPersonPhone)
				newCommercial = new CommercialCustomer(UniqueIDFactory.generateUniqueID(), customerFullName, customerEmail, customerCreditRating, contactPerson, contactPersonPhone);
				customerArray.add(newCommercial);
			} catch (RuntimeException e) {
				System.err.print("Invalid input");
			}
		} 
		
		for(Customer c : customerArray) {
			System.out.println(c);
		}
		
		while(!input.equals("c")) {
			System.out.print("Press 'c' to continue: ");
			input = userInput.next();
		}
	}
	
	public void createCheckingAccount() {
		double startingBalance = 0;
		Date newDate = new Date();
		String firstName = "", lastName = "";
		customerInput = "";
		Customer customer = null;
		boolean customerSelected = false;
		
		if(!customerArray.isEmpty()) {
			
			while(customerSelected == false) {
				System.out.println("Please enter the full name of the customer that you would like to add to the account");
				firstName = userInput.next();
				lastName = userInput.next();
				customerInput = firstName + " " + lastName;
				for(Customer c : customerArray) {
					if(c.getName().equals(customerInput)) {
						customer = c;
						customerSelected = true;
					}
				}
			}
			
			// Select customer
			try {
				System.out.print("\nHow much would you like to deposit for the starting balance of your account: ");
				startingBalance = userInput.nextDouble();
				
				//(String accountNumber, double accountBalance, Customer customer, Date dateCreated)
				Account chkAcct = new CheckingAccount(UniqueIDFactory.generateUniqueID(),startingBalance, customer, newDate);
				accountArray.add(chkAcct);
				System.out.println("Checking account has been successfully created. Your accountNumber is: " + chkAcct.getAccountNumber() + "\n");
			} catch (NumberFormatException n) {
				System.err.print("Invalid Number Format");
			}
			
		}else {
			System.out.println("There are no customers to make an account with...");
		}
		
		while(!input.equals("c")) {
			System.out.print("Press 'c' to continue: ");
			input = userInput.next();
		}
	}
	public void createGoldAccount() {
		
		double startingBalance = 0;
		Date newDate = new Date();
		String firstName = "", lastName = "";
		customerInput = "";
		Customer customer = null;
		boolean customerSelected = false;
		
		if(!customerArray.isEmpty()) {
			
			while(customerSelected == false) {
				System.out.println("Please enter the full name of the customer that you would like to add to the account");
				firstName = userInput.next();
				lastName = userInput.next();
				customerInput = firstName + " " + lastName;
				for(Customer c : customerArray) {
					if(c.getName().equals(customerInput)) {
						customer = c;
						customerSelected = true;
					}
				}
			}
			try {
				System.out.print("\nHow much would you like to deposit for the starting balance of your account: ");
				startingBalance = userInput.nextDouble();
			}catch(NumberFormatException n) {
				System.err.print("Invalid number format");
			}
			Account goldAcct = new GoldAccount(UniqueIDFactory.generateUniqueID(),startingBalance, customer, newDate);
			accountArray.add(goldAcct);
			System.out.println("Gold account has been successfully created. Your accountNumber is: " + goldAcct.getAccountNumber() + "\n");
		}else {
			System.out.println("There are no customers to make an account with...");
		}
		
		//GoldAccount(long accountNumber, double accountBalance, Customer customer, Date dateCreated, double interestRate) 
		while(!input.equals("c")) {
			System.out.print("Press 'c' to continue: ");
			input = userInput.next();
		}
	}
	public void createRegularAccount() { 
		double startingBalance = 0;
		Date newDate = new Date();
		String firstName = "", lastName = "";
		customerInput = "";
		Customer customer = null;
		boolean customerSelected = false;
		
		if(!customerArray.isEmpty()) {
			
			while(customerSelected == false) {
				System.out.println("Please enter the full name of the customer that you would like to add to the account");
				firstName = userInput.next();
				lastName = userInput.next();
				customerInput = firstName + " " + lastName;
				for(Customer c : customerArray) {
					if(c.getName().equals(customerInput)) {
						customer = c;
						customerSelected = true;
					}
				}
			}
		
		//RegularAccount(long accountNumber, double accountBalance, Customer customer, Date dateCreated, double interestRate)
			try {
				System.out.print("\nWhat is the initial deposit into the account: ");
				startingBalance = userInput.nextDouble();
			} catch(NumberFormatException n) {
				System.err.print("Invalid Number Format");
			}
			Account regularAcct = new RegularAccount(UniqueIDFactory.generateUniqueID(), startingBalance, customer, newDate);
			accountArray.add(regularAcct);
			System.out.println("Regular Account has been created. Your account number is : " + regularAcct.getAccountNumber() + "\n");
		
		}else {
			System.out.println("There are no customers to make an account with...");
		}
		
		while(!input.equals("c")) {
			System.out.print("\nPress 'c' to continue: ");
			input = userInput.next();
		}
	
	}
	
	/**
	 * Deposit an amount of money into a selected account
	 * 
	 * 
	 * @return true if account found or false if not found
	 */
	public boolean depositIntoAccount() {
		double depositAmount = -1; // Amount of money being deposited
		boolean accountFound = false; //Changes to true if account is found
		
		if(!accountArray.isEmpty()) {
			System.out.print("\nEnter the account number that you would like to deposit into: ");
			accountNumberInput = userInput.nextLong();
			
			for(Account a : accountArray) {
				if(a.getAccountNumber() == accountNumberInput) {
					while(depositAmount < 0) {
						System.out.print("Please enter the amount that you would like to deposit into your account: ");
						depositAmount = userInput.nextDouble();
					}
					a.deposit(depositAmount);
					accountFound = true;
					break;
				}
				
			}
			if (accountFound == false) {
				System.out.println("The account could not be found.");
			}
		} else {
			System.out.print("There are no accounts to deposit into.");
			
			while(!input.equals("c")) {
				System.out.print("\nPress 'c' to continue: ");
				input = userInput.next();
			}
		}
		
		return accountFound;
		
	}
	
	/**
	 * Withdraw an amount of money from a selected account. If total amount in account is less than desired to withdraw, withdraw the current balance
	 * 
	 * 
	 * @return true if account found or false if not found
	 */
	public boolean withdrawFromAccount() {
		double withdrawAmount = -1;
		boolean accountFound = false;
		
		if(!accountArray.isEmpty()) {
			System.out.print("\nEnter the account number that you would like to withdraw from: ");
			accountNumberInput = userInput.nextLong();
			
			for(Account a : accountArray) {
				if(a.getAccountNumber() == accountNumberInput){
					while(withdrawAmount < 0) {
						System.out.print("How much would you like to withdraw from your account: ");
						withdrawAmount = userInput.nextDouble();
					}
					accountFound = true;
					a.withdraw(withdrawAmount);
					break;
				} 
			}
			if (accountFound == false) {
				System.out.println("The account could not be found.");
			}
		} else {
			System.out.print("There are no accounts to withdraw from.");
			
			while(!input.equals("c")) {
				System.out.print("\nPress 'c' to continue: ");
				input = userInput.next();
			}
		}
			return accountFound;
	}
	
	/**
	 * User enters a account number, and if the account is in the accountArray, account information, number, and balance will be displayed.
	 */
	public void displayAccountInfo() { 
		boolean accountFound = false;
		
		if(!accountArray.isEmpty()) { 
			System.out.print("\nEnter the account number for the account that you would like to view: ");
			accountNumberInput = userInput.nextLong();
			
			for(Account a : accountArray) {
				if(a.getAccountNumber() == accountNumberInput) {
					System.out.println("\nCustomer Name\t\tAccountNumber\t\tAccount Balance\t\tAccount Email\n");
					System.out.println(a.getCustomer().getName() + "\t\t" + a.getAccountNumber() + "\t\t" + a.getAccountBalance() + "\t\t" + a.getCustomer().getEmail() + "\n");
					accountFound = true;
					break;
				}
				
				}
				if (accountFound == false) {
					System.out.print("Account could not be found.");
				}
		} else {
			System.out.print("There are no accounts in the bank.");
		}
			
		while(!input.equals("c")) {
			System.out.print("\nPress 'c' to continue: ");
			input = userInput.next();
		}
		
	}
	
	/**
	 * Enter an account number, if the number is in the array, it will be removed from the accountArray
	 */
	public void removeAccount() {
		boolean accountFound = false;
		long accountNumber = 0;
		
		if(!accountArray.isEmpty()) {
			try {
				System.out.print("\nEnter the account number that you would like to remove: ");
				accountNumberInput = userInput.nextLong();
				
				for(int i = 0; i <= accountArray.size(); i++) {
					
					accountNumber = accountArray.get(i).getAccountNumber();
					
					if(accountNumber == accountNumberInput) {
						accountFound = true;
						accountArray.remove(i);
						accountArray.trimToSize();
						System.out.println("Account " + accountNumberInput + " has been removed.");
						displayAllAccounts();
					}
				}
				if (accountFound == false) {
					System.out.println("Account could not be found. ");
				}
				
			}catch (InputMismatchException e) {
				System.out.println("Invalid account number");
			}
		}else {
			System.out.println("There are no accounts in the bank.\n");
		}
		
		while(!input.equals("c")) {
			System.out.print("\nPress 'c' to continue: ");
			input = userInput.next();
		}
	}
	
	/**
	 * Applies appropriate account updates for each account in the accountArray for each type of account.
	 */
	public void applyAccountUpdates() {
		if(!accountArray.isEmpty()) {
			for(Account a: accountArray) {
				if (a instanceof CheckingAccount) {
					((CheckingAccount) a).deductFees();
				} else if (a instanceof GoldAccount) {
					((GoldAccount) a).calculateInterest();
				} else if (a instanceof RegularAccount) {
					((RegularAccount) a).calculateInterest();
				}
			}
			System.out.print("Account updates applied successfully!");
		} else {
			System.out.print("There are no accounts in the bank.");
		}
		
		while(!input.equals("c")) {
			System.out.print("\nPress 'c' to continue: ");
			input = userInput.next();
		}
	}
	
	/**
	 * Adds up the totalBalance for all accounts in the Bank, and the total balance for each type of account in the Bank. 
	 * The method also counts how many of each type of account are in the array, and displays all the information to the user. 
	 */
	public void displayBankStatistics() {
		double totalBalance = 0;
		double totalCheckingBalance = 0;
		double totalGoldBalance = 0;
		double totalRegularBalance = 0;
		double averageAcctBalance = 0;
		
		int checkingAcctCounter = 0;
		int goldAcctCounter = 0;
		int regularAcctCounter = 0;
		int emptyAcctCounter = 0;
		double largestAccountBalance = 0;
		String largestAccount = "";
		
		
		if(!accountArray.isEmpty()) {
			System.out.println("\nTotal balance of all accounts: ");
			
			for(Account a : accountArray) {
				totalBalance += a.getAccountBalance();
				if(a.getAccountBalance() > largestAccountBalance) {
					largestAccountBalance = a.getAccountBalance();
					largestAccount = a.getCustomer().getName();
				}
			}
			
				averageAcctBalance = totalBalance / accountArray.size();
			
			for(Account a : accountArray) {
				if(a instanceof CheckingAccount) {
					totalCheckingBalance += a.getAccountBalance();
					checkingAcctCounter++;
				} else if (a instanceof GoldAccount) {
					totalGoldBalance += a.getAccountBalance();
					goldAcctCounter++;
				} else if (a instanceof RegularAccount) {
					totalRegularBalance += a.getAccountBalance();
					regularAcctCounter++;
				}
				
				if(a.getAccountBalance() == 0) {
					emptyAcctCounter++;
				}
				
			}
			System.out.println("\tThe total balance of all accounts in the bank is: " + totalBalance);
			System.out.println("\tThe average account balance is $" + averageAcctBalance + " of all accounts.");
			System.out.println("\tThe total balance of all Checking accounts in the bank is: $" + totalCheckingBalance);
			System.out.println("\tThe total balance of all Gold accounts in the bank is: $" + totalGoldBalance);
			System.out.println("\tThe total balance of all Regular accounts in the bank is: $" + totalRegularBalance);
			System.out.println("\tThere is a total of: " + checkingAcctCounter + " checking account(s), " + goldAcctCounter + " gold account(s), and " + regularAcctCounter + " regular account(s) in the bank.");
			System.out.println("\tThere are a total of " + emptyAcctCounter + " empty accounts in the bank.");
			System.out.println("\tThe customer with the largest balance is: " + largestAccount);
			
			while(!input.equals("c")) {
				System.out.print("\nPress 'c' to continue: ");
				input = userInput.next();
			}
		} else {
			System.out.println("There are currently no accounts in the bank.");
			
			while(!input.equals("c")) {
				System.out.print("\nPress 'c' to continue: ");
				input = userInput.next();
			}
		}
	}
	
	/**
	 * The method displays the customerName, accountNumber, and accountBalance of every account in the array
	 *  
	 */
	public void displayAllAccounts() {
		
		while(!input.equals("c")) {
			if(!accountArray.isEmpty()) {		
				System.out.println("\nCustomer Information\tAccountNumber");
				
				for(Account a : accountArray) {
					//System.out.println(a.getCustomer().getName() + "\t\t" + a.getAccountNumber() );
					System.out.format("%-14s\t\t%13d\n", a.getCustomer().getName(), a.getAccountNumber());
				}
				
				
			} else {
				System.out.print("There are currently no accounts in the bank.");
			}
			
			System.out.print("\nPress 'c' to continue: ");
			input = userInput.next();
		}
	}
	/**
	 * 
	 *
	 */
	public void exit() {// throws exception every time
		
		File f = new File("bankdata.ser");
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(f));
			output.writeObject(accountArray);
			System.out.println("Saved");
		} catch (IOException ioException) {
			System.err.println("Error opening file.");
			//ioException.printStackTrace();
			System.out.println();
		}
		
	}
	
	/**
	 * @throws ClassNotFoundException 
	 * 
	 */
	public void openFileForReading() throws ClassNotFoundException {
		
		File f = new File("bankdata.ser");
		if(f.exists()) {

			try {
				ObjectInputStream input = new ObjectInputStream(new FileInputStream(f));

				accountArray = (ArrayList<Account>) input.readObject();
	
			} catch(IOException ioException) {
				System.err.println("Error opening file.");
				//ioException.printStackTrace();
				System.out.println();
			}
	}
	}
}
