package com.charity_org.demo.Controllers;


import com.charity_org.demo.Enums.Currencies;
import com.charity_org.demo.Models.MoneyDonnation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/money-type")
public class MoneyTypeController {
    // Mapping for showing the money donation form
    @GetMapping({"", "/"})
    public String showMoneyDonationForm(Model model) {
        // Create a new MoneyDonation object to bind to the form
        model.addAttribute("moneyDonation", new MoneyDonnation());

        // Add the enum for currency types to the model
        List<Currencies> currencies = Arrays.asList(Currencies.values());
        model.addAttribute("currencies", currencies);

        return "MoneyDonationView";  // Return the view name (moneyDonationForm.html)
    }

    // Handle the form submission for money donation
    @PostMapping("/submitDonation")
    public String submitMoneyDonation(@ModelAttribute("moneyDonation") MoneyDonnation moneyDonation, Model model) {
        // Process the form data (e.g., save the donation to a database or perform other actions)

        // For now, just adding the donation object to the model to display a confirmation

        // Return a confirmation view (could be a new page or the same page with a success message)
        return "donationConfirmation";  // Return the confirmation view
    }
}
