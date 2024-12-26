package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Classes.StrategyComponents.IPaymentMethod;
import com.charity_org.demo.Models.Model.Visa;
import com.charity_org.demo.Models.Repository.VisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisaService implements IPaymentMethodService{

    @Autowired
    private VisaRepository visaRepository;
    @Override
    public boolean processPayment(IPaymentMethod paymentMethod) {
        if(!visaRepository.findAll().contains((Visa) paymentMethod)) {
            Visa result = visaRepository.save((Visa) paymentMethod);
        }
        return true;
    }
}

