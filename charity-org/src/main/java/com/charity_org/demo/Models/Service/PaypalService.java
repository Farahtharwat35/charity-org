package com.charity_org.demo.Models.Service;



import com.charity_org.demo.Models.IPaymentMethod;
import com.charity_org.demo.Models.Paypal;
import com.charity_org.demo.Models.repository.PaypalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service
public class PaypalService implements IPaymentMethodService {
    @Autowired
    private PaypalRepository paypalRepository;


    @Override
    public boolean processPayment(IPaymentMethod paymentMethod) {
        if(!paypalRepository.findAll().contains((Paypal)paymentMethod)) {
            Paypal result = paypalRepository.save((Paypal) paymentMethod);
        }
            return true;
    }
}

