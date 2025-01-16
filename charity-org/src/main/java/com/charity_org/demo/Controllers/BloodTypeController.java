package com.charity_org.demo.Controllers;


import com.charity_org.demo.Enums.BloodType;

import com.charity_org.demo.Enums.DonationStatus;
import com.charity_org.demo.Middlware.cookies.CookieHandler;
import com.charity_org.demo.Models.Model.BloodDonnation;
import com.charity_org.demo.Models.Model.Donation;
import com.charity_org.demo.Models.Model.DonationDetails;
import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Models.Service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@RequestMapping("/blood-type")
public class BloodTypeController {
    @Autowired
    DonationDetailsService donationDetailsService;

    @Autowired
    DonationTypeService donationTypeService;

    @Autowired
    CookieHandler cookieHandler;

    @Autowired
    DonationService donationService;

    DonationDetails newdonationDetails;

    BloodDonnation myBloodDonnation;

    @GetMapping({"/", ""})
    public String getBloodPage(Model model) {
        // Add an empty BloodDonnation object to the model for form binding
        model.addAttribute("bloodDonnation", new BloodDonnation());
        // Add BloodType enum values for the dropdown
        model.addAttribute("bloodTypes", BloodType.values());
        return "BloodDonationView"; // View name for the form page
    }

    @PostMapping("/submitDonation")
    public String submitDonation(@ModelAttribute("bloodDonnation") BloodDonnation bloodDonnation,
                                 @RequestParam("paymentMethod") String paymentMethod,
                                 HttpServletRequest request,
                                 RedirectAttributes redirectAttributes) {
        myBloodDonnation = bloodDonnation;
        bloodDonnation.setHasCost(false);
        bloodDonnation.setCost(0);

        newdonationDetails = new DonationDetails();
        newdonationDetails.setDonationType(donationTypeService.saveDonationType(bloodDonnation));
        newdonationDetails.setQuantity(1); // Blood donations often don't have quantities
        ShippingFee shippingFee = new ShippingFee(donationDetailsService);
        BloodDrawFees newBloodDrawFees = new BloodDrawFees(shippingFee);
        newdonationDetails.setSubTotalPrice(newBloodDrawFees.calculate_price(newdonationDetails));
        newdonationDetails.setDonation_invoice_Description(newBloodDrawFees.display_invoice_details(newdonationDetails));

        if(Objects.equals(paymentMethod, "Cash")){
            return confirmPayment(redirectAttributes,request);
        }

        return paymentMethod;
    }

    @GetMapping("/submitPaymentSuccessful")
    public String confirmPayment(RedirectAttributes redirectAttributes, HttpServletRequest request){
        if (newdonationDetails != null){//here put the condition
            Donation donation = new Donation();
            User currentUser = cookieHandler.getUserFromSession(request);
            donation.setUser(currentUser);
            donation.addTodonationDetials(donationDetailsService.saveDonationDetails(newdonationDetails));
            newdonationDetails.setDonation(donation);
            donation.setDonationTotalPrice(newdonationDetails.getSubTotalPrice());
            donation.setStatus(DonationStatus.COMPLETED);
            donationService.save(donation);
            redirectAttributes.addFlashAttribute("donationDetails", newdonationDetails);
            redirectAttributes.addFlashAttribute("bloodDonnation", myBloodDonnation);
        }
        return "redirect:/getReceipt";
    }
}


