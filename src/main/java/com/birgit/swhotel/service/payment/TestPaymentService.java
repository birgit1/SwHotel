
package com.birgit.swhotel.service.payment;

import com.birgit.swhotel.service.payment.AbstractPaymentService;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;


@RequestScoped @Alternative
public class TestPaymentService implements AbstractPaymentService, Serializable 
{
    @Inject
    private Logger logger;

    @Override
    public boolean pay(double amount) 
    {
        logger.info("testpaymentService");
        return true;
    }
    
}