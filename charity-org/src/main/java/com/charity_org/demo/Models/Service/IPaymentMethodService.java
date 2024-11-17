package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Models.IPaymentMethod;
import com.charity_org.demo.Models.Paypal;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface IPaymentMethodService {
    public boolean processPayment(IPaymentMethod paymentMethod);
}
