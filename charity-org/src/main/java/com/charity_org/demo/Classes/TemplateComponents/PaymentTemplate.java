package com.charity_org.demo.Classes.TemplateComponents;

import com.charity_org.demo.Classes.StrategyComponents.IPaymentMethod;

public abstract class PaymentTemplate implements IPaymentMethod{
    // Template method
    public final boolean processPayment(IPaymentMethod paymentMethod) {
        if (!validatePaymentMethod(paymentMethod)) {
            return false;
        }
        if (!isPaymentMethodSaved(paymentMethod)) {
            savePaymentMethod(paymentMethod);
        }
        return completePayment();
    }

    // Steps to be implemented or customized by subclasses
    protected abstract boolean validatePaymentMethod(IPaymentMethod paymentMethod);
    protected abstract boolean isPaymentMethodSaved(IPaymentMethod paymentMethod);
    protected abstract void savePaymentMethod(IPaymentMethod paymentMethod);;

    protected boolean completePayment(){
        return true;
    }

}
