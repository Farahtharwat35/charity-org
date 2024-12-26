package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Classes.StrategyComponents.IPaymentMethod;

public interface IPaymentMethodService {
    public boolean processPayment(IPaymentMethod paymentMethod);
}
