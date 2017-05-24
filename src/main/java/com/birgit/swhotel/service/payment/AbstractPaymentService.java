
package com.birgit.swhotel.service.payment;

import javax.transaction.Transactional;


public interface AbstractPaymentService 
{
    @Transactional
    public boolean pay(double amount, String paymentEmail, String paymentPassword);
}
