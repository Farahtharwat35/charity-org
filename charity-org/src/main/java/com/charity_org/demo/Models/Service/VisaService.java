package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Models.PaymentMethod;
import com.charity_org.demo.Models.Paypal;
import com.charity_org.demo.Models.VISA;
import com.charity_org.demo.Models.repository.VisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class VisaService implements IPaymentMethodService{

    @Autowired
    private VisaRepository visaRepository;
    @Override
    public boolean processPayment(PaymentMethod paymentMethod) {
        if(!visaRepository.findAll().contains((VISA) paymentMethod)) {
            VISA result = visaRepository.save((VISA) paymentMethod);
        }
        return true;
    }
}

