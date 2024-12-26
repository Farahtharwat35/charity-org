package com.charity_org.demo.Models.Service;



import com.charity_org.demo.Classes.StrategyComponents.IPaymentMethod;
import com.charity_org.demo.Models.Model.Paypal;
import com.charity_org.demo.Models.Repository.PaypalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

