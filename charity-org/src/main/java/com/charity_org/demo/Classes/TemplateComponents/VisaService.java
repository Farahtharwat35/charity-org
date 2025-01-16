package com.charity_org.demo.Classes.TemplateComponents;

import com.charity_org.demo.Classes.StrategyComponents.IPaymentMethod;
import com.charity_org.demo.Models.Model.Visa;
import com.charity_org.demo.Models.Repository.VisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisaService extends PaymentTemplate{

    @Autowired
    private VisaRepository visaRepository;

    @Override
    protected boolean validatePaymentMethod(IPaymentMethod paymentMethod) {
        // Custom validation logic for Visa
        if (paymentMethod instanceof Visa) {
            return ((Visa) paymentMethod).getCardNumber() != null;
        }
        return false;
    }

    @Override
    protected boolean isPaymentMethodSaved(IPaymentMethod paymentMethod) {
        return visaRepository.findAll().contains((Visa) paymentMethod);
    }

    @Override
    protected void savePaymentMethod(IPaymentMethod paymentMethod) {
        visaRepository.save((Visa) paymentMethod);
    }
}

