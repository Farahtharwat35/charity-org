package com.charity_org.demo.Classes.TemplateComponents;



import com.charity_org.demo.Classes.StrategyComponents.IPaymentMethod;
import com.charity_org.demo.Models.Model.Paypal;
import com.charity_org.demo.Models.Repository.PaypalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaypalService extends PaymentTemplate {
    @Autowired
    private PaypalRepository paypalRepository;

    @Override
    protected boolean validatePaymentMethod(IPaymentMethod paymentMethod) {
        // Custom validation logic for PayPal
        if (paymentMethod instanceof Paypal) {
            return ((Paypal) paymentMethod).getPaypalEmail() != null;
        }
        return false;
    }

    @Override
    protected boolean isPaymentMethodSaved(IPaymentMethod paymentMethod) {
        return paypalRepository.findAll().contains((Paypal) paymentMethod);
    }

    @Override
    protected void savePaymentMethod(IPaymentMethod paymentMethod) {
        paypalRepository.save((Paypal) paymentMethod);
    }

}

