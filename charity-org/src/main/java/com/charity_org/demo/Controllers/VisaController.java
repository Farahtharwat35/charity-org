package com.charity_org.demo.Controllers;

import com.charity_org.demo.Models.Paypal;
import com.charity_org.demo.Models.Service.IPaymentMethodService;
import com.charity_org.demo.Models.Service.PaypalService;
import com.charity_org.demo.Models.Service.VisaService;
import com.charity_org.demo.Models.VISA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/visa")
public class VisaController {

    @Autowired
    private VisaService visaService;

    @GetMapping("")
    public String visaPage(Model model) {
        model.addAttribute("visa", new VISA()); // Add an empty Paypal object to the model
        return "VisaView"; // The HTML form view
    }

    // POST Request to handle form submission
    @PostMapping("/save")
    public String processPayment(@ModelAttribute VISA visa, Model model) {
        boolean result = visaService.processPayment(visa);

        if (result) {
            model.addAttribute("message", "Payment processed successfully!");
        } else {
            model.addAttribute("message", "Payment processing failed. Please try again.");
        }

        return "VisaView"; // Render the same view with the message
    }
}