package com.charity_org.demo.Models.Service;



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
    public boolean processPayment(@RequestBody Map<String, String> jsonMap) {
        if (jsonMap.containsKey("paypal-email")) {
            String email = (String) jsonMap.get("paypal-email");
            if (email.contains("@")) {
                String phoneNumber = (String) jsonMap.get("password");
                paypalRepository.save(new Paypal(email, phoneNumber));
                return true;
            }
        }
        return false;
    }
}

