package com.charity_org.demo.Controllers;

import com.charity_org.demo.Classes.State.PendingDonation;
import com.charity_org.demo.Middlware.cookies.CookieHandler;
import com.charity_org.demo.Models.Model.*;
import com.charity_org.demo.Models.Service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/money-type")
public class MoneyTypeController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    DonationDetailsService donationDetailsService;

    @Autowired
    DonationTypeService donationTypeService;

    @Autowired
    CookieHandler cookieHandler;

    @Autowired
    DonationService donationService;

    MoneyDonnation myMoneyDonnation;
    DonationDetails newdonationDetails;

    @GetMapping({"", "/"})
    public String showMoneyDonationForm(Model model) {
        // Create a new MoneyDonation object to bind to the form
        model.addAttribute("moneyDonation", new MoneyDonnation());

        // Add the currencies from database to the model
        List<Currency> currencies = currencyService.getAllCurrencies();
        model.addAttribute("currencies", currencies);

        return "MoneyDonationView";
    }

    @PostMapping("/submitDonation")
    public String submitMoneyDonation(
            @ModelAttribute("moneyDonation") MoneyDonnation moneyDonation,
            @RequestParam("paymentMethod") String paymentMethod,
            Model model,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {

        // Store the money donation
        this.myMoneyDonnation = moneyDonation;

        // Create donation details
        newdonationDetails = new DonationDetails();
        newdonationDetails.setDonationType(donationTypeService.saveDonationType(moneyDonation));

        // Calculate tax and set details
        Tax newTax = new Tax(donationDetailsService);
        newdonationDetails.setSubTotalPrice(newTax.calculate_price(newdonationDetails));
        newdonationDetails.setDonation_invoice_Description(newTax.display_invoice_details(newdonationDetails));

        if (Objects.equals(paymentMethod, "Cash")) {
            return confirmPayment(redirectAttributes, request);
        }

        return paymentMethod;
    }

    @GetMapping("/submitPaymentSuccessful")
    public String confirmPayment(RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (newdonationDetails != null) {
            Donation donation = new Donation();
            donation.setDonationStatus(new PendingDonation());

            User currentUser = cookieHandler.getUserFromSession(request);
            donation.setUser(currentUser);

            DonationDetails savedDetails = donationDetailsService.saveDonationDetails(newdonationDetails);
            donation.addTodonationDetials(savedDetails);
            newdonationDetails.setDonation(donation);

            donationService.save(donation);

            redirectAttributes.addFlashAttribute("user", currentUser);
            redirectAttributes.addFlashAttribute("donationDetails", newdonationDetails);
            redirectAttributes.addFlashAttribute("moneyDonation", myMoneyDonnation);
        }

        return "redirect:/getReceipt";
    }
}