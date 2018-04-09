/*
 * PersonalCustomer.java
 * 
 * A specialized customer representing a personal
 * customer
 *
 * @author AMA
 * @version 1.0
*/
package com.bank.customer;

public class PersonalCustomer extends Customer{
	
    private String homePhone;
    private String workPhone;
    
     /**
	 * @param customerId
	 * @param name
	 * @param email
	 * @param homePhone
	 * @param workPhone
	 */
	public PersonalCustomer(long customerId, String name, String email, String homePhone, String workPhone) {
		super(customerId, name, email);
		this.homePhone = homePhone;
		this.workPhone = workPhone;
	}

	public String getHomePhone(){
        return homePhone;
    }
    
    public void setHomePhone(String homePhone){
        this.homePhone = homePhone;
    }
    
     public String getWorkPhone(){
        return workPhone;
    }
    
    public void setWorkPhone(String workPhone){
        this.workPhone = workPhone;
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
               "\nHome Phone: " + homePhone +
               "\nWork Phone: " + workPhone;
    }
}
