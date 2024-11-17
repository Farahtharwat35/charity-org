package com.charity_org.demo.Controllers;


import com.charity_org.demo.Models.Service.IPaymentMethodService;
import com.charity_org.demo.Models.Service.PaypalService;
import com.charity_org.demo.Models.Service.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment/api")
public class PaymentController {

    @Autowired
    private VisaService visaService;

    @Autowired
    private PaypalService paypalService;

//    @GetMapping("/visa")
//    public String visaPage(){
//        return "VisaView";
//    }
//    @GetMapping("/paypal")
//    public String paypalPage(){
//        return "PaypalView";
//    }

//    @CrossOrigin(origins = "*")
//    @PostMapping("/save")
//    public String processPayment(@RequestBody Map<String, Object> jsonMap) {
//
//        IPaymentMethodService paymentMethod;
//        Boolean result;
//
//        if (jsonMap.containsKey("cvv")) {
//            paymentMethod = visaService;
//            result = paymentMethod.processPayment(jsonMap);
//        } else if (jsonMap.containsKey("paypal-email")) {
//            paymentMethod = paypalService;
//            result = paymentMethod.processPayment(jsonMap);
//        } else {
//            return "{\"status\": \"error\", \"message\": \"Invalid Input Format\"}";
//        }
//
//        return result
//                ? "{\"status\": \"success\", \"message\": \"Payment Processed Successfully\"}"
//                : "{\"status\": \"error\", \"message\": \"Payment Processing Failed\"}";
//    }
}
