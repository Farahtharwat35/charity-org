package com.charity_org.demo.Controllers;

import com.charity_org.demo.Classes.StrategyComponents.IPaymentMethod;
import com.charity_org.demo.Classes.TemplateComponents.PaymentTemplate;
import com.charity_org.demo.Classes.TemplateComponents.PaymobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/paymob")
public class PaymobController {

    @Autowired
    private PaymobService paymobService;

    @GetMapping("/save")
    public String initiatePayment(@RequestHeader(value = "Referer", required = false) String referer) {
        String path = referer.replace("http://localhost:8080/", "");
        // Get the first segment of the path
        String firstPath = path.split("/")[0];
        String redirectUrl = "http://localhost:8080/" + firstPath + "/submitPaymentSuccessful"; // Render the same view with the message
        paymobService.setRedirectUrl(redirectUrl);
        paymobService.processPayment(paymobService);

        // Redirect to Paymob payment link
        return "redirect:" + paymobService.getPaymobUrl();
    }


}

