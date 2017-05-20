
package com.birgit.swhotel.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;

/*
 all data which are needed for the payment webservice
*/

@Embeddable
public class Payment implements Serializable
{
    private int accountNumber;
    
    
    
}
