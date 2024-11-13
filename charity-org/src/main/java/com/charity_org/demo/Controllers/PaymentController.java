package com.charity_org.demo.Controllers;

import com.charity_org.demo.Models.Service.IPaymentMethodService;
import com.charity_org.demo.Models.Service.PaypalService;
import com.charity_org.demo.Models.Service.VisaService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/payment-details-api")
public class PaymentController {
    private IPaymentMethodService paymentMethod;
    Boolean x;
    @GetMapping("/save")
    @PostMapping("/save")
    public String processPayment(@RequestBody Map<String, String> jsonMap)
    {

        if(jsonMap.containsKey("cvv"))
        {
            paymentMethod = new VisaService();
            x = paymentMethod.processPayment(jsonMap);
        }
        else if(jsonMap.containsKey("paypal-email"))
        {
            paymentMethod = new PaypalService();
            x = paymentMethod.processPayment(jsonMap);
        }
        else{
            return "Invalid Input Format";
        }
        return "Payment Processed Successfully";

    }

}
