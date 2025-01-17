package com.charity_org.demo.Controllers;

import com.charity_org.demo.Classes.State.CompletedDonation;
import com.charity_org.demo.Classes.State.PendingDonation;
import com.charity_org.demo.Enums.ClothesSize;
import com.charity_org.demo.Enums.DonationStatus;
import com.charity_org.demo.Enums.Season;
import com.charity_org.demo.Middlware.cookies.CookieHandler;
import com.charity_org.demo.Models.Model.ClothesDonnation;
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
@RequestMapping("/clothes-type")
public class ClothesTypeController {
    @Autowired
    private ClothesTypeService clothesTypeService;
    @Autowired
    DonationDetailsService donationDetailsService;

    @Autowired
    DonationTypeService donationTypeService;

    @Autowired
    CookieHandler cookieHandler;

    @Autowired
    DonationService donationService;

    DonationDetails newdonationDetails;

    ClothesDonnation myClothesDonnation;

//    @Autowired
//    private ClothesDonnationType clothesDonnationService;

    // Display the clothes donation form
    @GetMapping({"/", ""})
    public String showDonationForm(Model model) {
        model.addAttribute("clothesDonnation", new ClothesDonnation());
        model.addAttribute("clothesSizes", ClothesSize.values());
        model.addAttribute("seasons", Season.values());
        model.addAttribute("clothingTypes", clothesTypeService.getAllClothesTypes());
        return "ClothesDonationView";
    }

    @PostMapping("/submitDonation")
    public String submitDonation(@ModelAttribute ClothesDonnation clothesDonnation,
                                 @RequestParam("paymentMethod") String paymentMethod,
                                 HttpServletRequest request,
                                 RedirectAttributes redirectAttributes) {
        myClothesDonnation = clothesDonnation;

        clothesDonnation.setHasCost(false);
        clothesDonnation.setCost(0);

        newdonationDetails = new DonationDetails();
        newdonationDetails.setDonationType(donationTypeService.saveDonationType(clothesDonnation));
        newdonationDetails.setQuantity(clothesDonnation.getQuantity());
        ShippingFee shippingFee = new ShippingFee(donationDetailsService);
        newdonationDetails.setSubTotalPrice(shippingFee.calculate_price(newdonationDetails));
        newdonationDetails.setDonation_invoice_Description(shippingFee.display_invoice_details(newdonationDetails));

        if(Objects.equals(paymentMethod, "Cash")){
            return confirmPayment(redirectAttributes,request);
        }

        return paymentMethod;
    }
    @GetMapping("/submitPaymentSuccessful")
    public String confirmPayment(RedirectAttributes redirectAttributes, HttpServletRequest request){
        if (newdonationDetails != null){//here put the condition
            Donation donation = new Donation();
            donation.setDonationStatus(new PendingDonation());
            User currentUser = cookieHandler.getUserFromSession(request);
            donation.setUser(currentUser);
            donation.addTodonationDetials(donationDetailsService.saveDonationDetails(newdonationDetails));
            newdonationDetails.setDonation(donation);
            donation.setDonationTotalPrice(newdonationDetails.getSubTotalPrice());
            donation.setDonationStatus(new CompletedDonation());
            donationService.save(donation);
            redirectAttributes.addFlashAttribute("donationDetails", newdonationDetails);
            redirectAttributes.addFlashAttribute("clothesDonnation", myClothesDonnation);
        }
        return "redirect:/getReceipt";
    }
}
