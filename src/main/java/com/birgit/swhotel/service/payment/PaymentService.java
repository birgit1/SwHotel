
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
     public boolean pay(double amount, String paymentEmail, String paymentPassword)
     {
         
         try { // Call Web Service Operation
             com.othr.kucko.paydudekucko.service.TransactionServiceService service = new com.othr.kucko.paydudekucko.service.TransactionServiceService();
             com.othr.kucko.paydudekucko.service.TransactionService port = service.getTransactionServicePort();
              // email
             // test@user.com
             java.lang.String arg0 = paymentEmail;
             // password: 123
             java.lang.String arg1 = paymentPassword;
             // amount
             long money = (long) amount*100;
             long arg2 = money;
             // iban
             // test 1 ; test@1.com; id: 54; 
             java.lang.String arg3 = "133754";
             // 
             // usage
             java.lang.String arg4 = "SW Hotel Booking";
             // TODO process result here
             boolean result = port.receiveExternalTransaction(arg0, arg1, arg2, arg3, arg4);
            
         } catch (Exception ex) 
         {
             logger.info("payment could not be processed");
             return false;
         }
         logger.info("payment successful");
         return true;
     }


}
