package com.charity_org.demo.Controllers;

import com.charity_org.demo.Enums.ClothesSize;
import com.charity_org.demo.Enums.ClothesType;
import com.charity_org.demo.Enums.DonationStatus;
import com.charity_org.demo.Enums.Season;
import com.charity_org.demo.Middlware.cookies.CookieHandler;
import com.charity_org.demo.Models.Model.ClothesDonnation;
import com.charity_org.demo.Models.Model.Donation;
import com.charity_org.demo.Models.Model.DonationDetails;
import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Models.Service.DonationDetailsService;
import com.charity_org.demo.Models.Service.DonationService;
import com.charity_org.demo.Models.Service.DonationTypeService;
import com.charity_org.demo.Models.Service.ShippingFee;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/clothes-type")
public class ClothesTypeController {
    @Autowired
    DonationDetailsService donationDetailsService;

    @Autowired
    DonationTypeService donationTypeService;

    @Autowired
    CookieHandler cookieHandler;

    @Autowired
    DonationService donationService;

    DonationDetails newdonationDetails;

//    @Autowired
//    private ClothesDonnationType clothesDonnationService;

    // Display the clothes donation form
    @GetMapping({"/", ""})
    public String showDonationForm(Model model) {
        model.addAttribute("clothesDonnation", new ClothesDonnation());
        model.addAttribute("clothesSizes", ClothesSize.values());
        model.addAttribute("seasons", Season.values());
        model.addAttribute("clothingTypes", ClothesType.values());
        return "ClothesDonationView";
    }

    @PostMapping("/submitDonation")
    public String submitDonation(@ModelAttribute ClothesDonnation clothesDonnation, @RequestParam("paymentMethod") String paymentMethod, HttpServletRequest request) {


        clothesDonnation.setHasCost(false);
        clothesDonnation.setCost(0);

        newdonationDetails = new DonationDetails();
        newdonationDetails.setDonationType(donationTypeService.saveDonationType(clothesDonnation));
        newdonationDetails.setQuantity(clothesDonnation.getQuantity());
        ShippingFee shippingFee = new ShippingFee(donationDetailsService);
        newdonationDetails.setSubTotalPrice(shippingFee.calculate_price(newdonationDetails));
        newdonationDetails.setDonation_invoice_Description(shippingFee.display_invoice_details(newdonationDetails));

        if(Objects.equals(paymentMethod, "Cash")){
            return confirmPayment(request);
        }

        return paymentMethod;
    }
    @GetMapping("/submitPaymentSuccessful")
    public String confirmPayment( HttpServletRequest request){
        if (newdonationDetails != null){//here put the condition
            Donation donation = new Donation();
            User currentUser = cookieHandler.getUserFromSession(request);
            donation.setUser(currentUser);
            donation.addTodonationDetials(donationDetailsService.saveDonationDetails(newdonationDetails));
            newdonationDetails.setDonation(donation);
            donation.setDonationTotalPrice(newdonationDetails.getSubTotalPrice());
            donation.setStatus(DonationStatus.COMPLETED);
            donationService.save(donation);
        }
        return "ListDonationTypesView";
    }
}
