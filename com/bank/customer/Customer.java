/*
 * Customer.java
 * 
 * This is a super class for other customers
 * to inherit.  A generic customer cannot be 
 * created and is therefore abstract. Also, if we want
 * to force future functionality to be implemented
 * by different customer types, abstract will allow for this.
 *
 * @author JIB
 * @version 1.1
*/
package com.bank.customer;

import java.io.Serializable;

public abstract class Customer implements Serializable {

    protected long customerId;
    protected String name;
    protected String email;
 
   /**
    * Constructor for creating customers to be used for accounts
	 * @param customerId
	 * @param name
	 * @param email
	 */
	public Customer(long customerId, String name, String email) {
		this.customerId = customerId;
		this.name = name;
		this.email = email;
	}
	public long getCustomerId() {
		return customerId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
    *
    * String representation of this object
    * 
    * @returns String attributes represented as a String
    */       
    @Override
    public String toString() {
        return "Name: " + name +
               "\nCustomer ID: " + customerId + 
               "\nEmail Address: " + email ;
    }
}
