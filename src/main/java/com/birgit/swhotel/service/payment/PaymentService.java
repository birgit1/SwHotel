
package com.birgit.swhotel.service.payment;

import com.birgit.swhotel.service.payment.AbstractPaymentService;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;


@RequestScoped @Alternative
public class PaymentService implements AbstractPaymentService, Serializable 
{

        @Inject
    private Logger logger;
        
    @Override
    public boolean pay(double amount) 
    {
        logger.info("payment WEBservice");
        // go to webservaci and pay
        return true;
    }
    
}
