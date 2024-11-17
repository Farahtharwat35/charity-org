package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Models.PaymentMethod;
import com.charity_org.demo.Models.Paypal;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface IPaymentMethodService {
    public boolean processPayment(PaymentMethod paymentMethod);
}
