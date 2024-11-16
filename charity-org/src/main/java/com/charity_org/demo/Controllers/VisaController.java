package com.charity_org.demo.Controllers;

import com.charity_org.demo.Models.Service.IPaymentMethodService;
import com.charity_org.demo.Models.Service.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/visa")
public class VisaController {

    @Autowired
    private VisaService visaService;

    @RequestMapping("")
    public String visaPage(){
        return "VisaView";
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/save")
    public String processPayment(@RequestBody Map<String, Object> jsonMap) {

        IPaymentMethodService paymentMethod;
        Boolean result;

        if (jsonMap.containsKey("cvv")) {
            paymentMethod = visaService;
            result = paymentMethod.processPayment(jsonMap);
        } else {
            return "{\"status\": \"error\", \"message\": \"Invalid Input Format\"}";
        }

        return result
                ? "{\"status\": \"success\", \"message\": \"Payment Processed Successfully\"}"
                : "{\"status\": \"error\", \"message\": \"Payment Processing Failed\"}";
    }
}