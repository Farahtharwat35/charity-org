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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

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
    public String submitMoneyDonation(@ModelAttribute("moneyDonation") MoneyDonnation moneyDonation, Model model, HttpServletRequest request) {
        // Create MoneyDonation object
        System.out.println("je;llllooo");
        System.out.println(moneyDonation.getCost());

        newdonationDetails= new DonationDetails();
        newdonationDetails.setDonationType(donationTypeService.saveDonationType(moneyDonation));
//        newdonationDetails.setSubTotalPrice(moneyDonation.getCost());
//        newdonationDetails.setSubTotalPrice(50);

        Tax newTax = new Tax(donationDetailsService);
        newdonationDetails.setSubTotalPrice(newTax.calculate_price(newdonationDetails));
        newdonationDetails.setDonation_invoice_Description(newTax.display_invoice_details(newdonationDetails));
        System.out.println(newdonationDetails.getDonation_invoice_Description());
        newdonationDetails.getSubTotalPrice();


        System.out.println(newdonationDetails.getSubTotalPrice());
        // Process the form data (e.g., save the donation to a database or perform other actions)

        // For now, just adding the donation object to the model to display a confirmation

        // Return a confirmation view (could be a new page or the same page with a success message)
        return "PaypalView";  // Return the confirmation view
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
