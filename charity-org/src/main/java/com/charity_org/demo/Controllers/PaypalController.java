package com.charity_org.demo.Controllers;

import com.charity_org.demo.Models.Model.Paypal;
import com.charity_org.demo.Models.Service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/paypal")
public class PaypalController {

    @Autowired
    private PaypalService paypalService;

    // GET Request to display the form
    @GetMapping({"", "/"})
    public String paypalPage(Model model) {
        model.addAttribute("paypal", new Paypal()); // Add an empty Paypal object to the model
        return "PaypalView"; // The HTML form view
    }

    // POST Request to handle form submission
    @PostMapping("/save")
    public String processPayment(@ModelAttribute Paypal paypal, Model model) {
        boolean result = paypalService.processPayment(paypal);

        if (result) {
            model.addAttribute("message", "Payment processed successfully!");
        } else {
            model.addAttribute("message", "Payment processing failed. Please try again.");
        }

        return "Confirmation"; // Render the same view with the message
    }
}
