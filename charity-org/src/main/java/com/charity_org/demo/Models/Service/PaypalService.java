package com.charity_org.demo.Models.Service;



import com.charity_org.demo.Enums.PaymentStatus;
import com.charity_org.demo.Models.PaymentMethod;
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
    public boolean processPayment(PaymentMethod paymentMethod) {
        if(!paypalRepository.findAll().contains((Paypal)paymentMethod)) {
            Paypal result = paypalRepository.save((Paypal) paymentMethod);

        }
        paymentMethod.setStatus(PaymentStatus.COMPLETED);
            return true;
    }
}

