package com.charity_org.demo.Classes.TemplateComponents;

import com.charity_org.demo.Classes.StrategyComponents.IPaymentMethod;

public abstract class PaymentTemplate implements IPaymentMethodService{
    // Template method
    public final boolean processPayment(IPaymentMethod paymentMethod) {
        if (!validatePaymentMethod(paymentMethod)) {
            return false;
        }
        if (!isPaymentMethodSaved(paymentMethod)) {
            savePaymentMethod(paymentMethod);
        }
        return completePayment(paymentMethod);
    }

    // Steps to be implemented or customized by subclasses
    protected abstract boolean validatePaymentMethod(IPaymentMethod paymentMethod);
    protected abstract boolean isPaymentMethodSaved(IPaymentMethod paymentMethod);
    protected abstract void savePaymentMethod(IPaymentMethod paymentMethod);

    protected final boolean completePayment(IPaymentMethod paymentMethod){
        System.out.println("Payment completed.");
        return true;
    }

}
