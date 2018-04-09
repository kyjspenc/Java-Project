/*
 * UniqueIDFactory.java
 * 
 * Utility class to generate unique ids
 *
 * @author AMA
 * @version 1.0
 */
package com.bank.utils;

import java.util.Calendar;

public class UniqueIDFactory {

     /*
     * Returns a unique long number
     * 
     * @returns unique id long
     */
    public static long generateUniqueID(){
        
        return Calendar.getInstance().getTimeInMillis();
        
    }
    
}
