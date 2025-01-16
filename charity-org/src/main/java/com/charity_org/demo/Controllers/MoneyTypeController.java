package com.charity_org.demo.Controllers;


import com.charity_org.demo.Enums.Currencies;

import com.charity_org.demo.Middlware.cookies.CookieHandler;
import com.charity_org.demo.Models.Model.Donation;
import com.charity_org.demo.Models.Model.DonationDetails;
import com.charity_org.demo.Models.Model.MoneyDonnation;
import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Models.Service.DonationDetailsService;
import com.charity_org.demo.Models.Service.DonationService;
import com.charity_org.demo.Models.Service.DonationTypeService;
import com.charity_org.demo.Models.Service.Tax;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/money-type")
public class MoneyTypeController {
    @Autowired
    DonationDetailsService donationDetailsService;

    @Autowired
    DonationTypeService donationTypeService;

    @Autowired
    CookieHandler cookieHandler;

    @Autowired
    DonationService donationService;

    DonationDetails newdonationDetails;
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
    public String submitMoneyDonation(@ModelAttribute("moneyDonation") MoneyDonnation moneyDonation, @RequestParam("paymentMethod") String paymentMethod, Model model, HttpServletRequest request) {
        // Create MoneyDonation object
        newdonationDetails= new DonationDetails();
        newdonationDetails.setDonationType(donationTypeService.saveDonationType(moneyDonation));

        Tax newTax = new Tax(donationDetailsService);
        newdonationDetails.setSubTotalPrice(newTax.calculate_price(newdonationDetails));
        newdonationDetails.setDonation_invoice_Description(newTax.display_invoice_details(newdonationDetails));

        if(Objects.equals(paymentMethod, "Cash")){
            return confirmPayment(request);
        }

        return paymentMethod;
    }

    @GetMapping("/submitPaymentSuccessful")
    public String confirmPayment( HttpServletRequest request){
        if(newdonationDetails != null) {
            //here put the condition
            Donation donation = new Donation();
            User currentUser = cookieHandler.getUserFromSession(request);
            donation.setUser(currentUser);
            donation.addTodonationDetials(donationDetailsService.saveDonationDetails(newdonationDetails));
            newdonationDetails.setDonation(donation);
            donationService.save(donation);
        }
        return "ListDonationTypesView";
    }
}
