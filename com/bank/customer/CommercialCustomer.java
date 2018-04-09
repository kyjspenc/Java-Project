/*
 * CommercialCustomer.java
 * 
 * A specialized customer representing a commercial customer
 *
 * @author AMA
 * @version 1.0
*/
package com.bank.customer;

public class CommercialCustomer extends Customer{

    private int creditRating;
    private String contactPerson;
    private String contactPersonPhone;
    
    /**
	 * @param customerId
	 * @param name
	 * @param email
	 * @param creditRating
	 * @param contactPerson
	 * @param contactPersonPhone
	 */
	public CommercialCustomer(long customerId, String name, String email, int creditRating, String contactPerson,
			String contactPersonPhone) {
		super(customerId, name, email);
		this.creditRating = creditRating;
		this.contactPerson = contactPerson;
		this.contactPersonPhone = contactPersonPhone;
	}

	public String getContactPerson(){
        return contactPerson;
    }
    
    public void setContactPerson(String contactPerson){
        this.contactPerson = contactPerson;
    }
    
    public String getContactPersonPhone(){
        return contactPersonPhone;
    }
    
    public void setContactPersonPhone(String contactPersonPhone){
        this.contactPersonPhone = contactPersonPhone;
    }
    
    /**
    *
    * String representation of this object
    * 
    * @returns String attributes represented as a String
    */       
    @Override
    public String toString() {
        return super.toString() + 
               "\nCredit Rating: " + creditRating +
               "\nContact Person: " + contactPerson + 
               "\nContact Person Phone: " + contactPersonPhone;
    }
}
