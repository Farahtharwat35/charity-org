package com.charity_org.demo.Classes.TemplateComponents;

import com.charity_org.demo.Classes.StrategyComponents.IPaymentMethod;

public interface IPaymentMethodService {
    public boolean processPayment(IPaymentMethod paymentMethod);
}
