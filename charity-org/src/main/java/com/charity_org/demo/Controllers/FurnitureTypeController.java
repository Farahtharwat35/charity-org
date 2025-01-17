package com.charity_org.demo.Controllers;

import com.charity_org.demo.Classes.State.CompletedDonation;
import com.charity_org.demo.Classes.State.PendingDonation;
import com.charity_org.demo.Enums.DonationStatus;
import com.charity_org.demo.Enums.FurnitureCondition;
import com.charity_org.demo.Models.Model.FurnitureType;
import com.charity_org.demo.Middlware.cookies.CookieHandler;
import com.charity_org.demo.Models.Model.Donation;
import com.charity_org.demo.Models.Model.DonationDetails;
import com.charity_org.demo.Models.Model.FurnitureDonnation;
import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Models.Service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/furniture-type")
public class FurnitureTypeController {
    @Autowired
    DonationDetailsService donationDetailsService;

    @Autowired
    DonationTypeService donationTypeService;

    @Autowired
    CookieHandler cookieHandler;

    @Autowired
    DonationService donationService;

    @Autowired
    private FurnitureTypeService furnitureTypeService;
    FurnitureDonnation myFurnitureDonation;
    DonationDetails newdonationDetails;
    // Mapping for showing the furniture donation form
    @GetMapping({"", "/"})
    public String showFurnitureDonationForm(Model model) {
        model.addAttribute("furnitureDonation", new FurnitureDonnation());

        // Get furniture types from database
        List<FurnitureType> furnitureTypes = furnitureTypeService.getAllFurnitureTypes();
        List<FurnitureCondition> furnitureConditions = Arrays.asList(FurnitureCondition.values());

        model.addAttribute("furnitureTypes", furnitureTypes);
        model.addAttribute("furnitureConditions", furnitureConditions);

        return "FurnitureDonationView";
    }

    // Handle the form submission for furniture donation
    @PostMapping("/submitDonation")
    public String submitFurnitureDonation(@ModelAttribute("furnitureDonation") FurnitureDonnation furnitureDonation, Model model,
                                          @RequestParam("paymentMethod") String paymentMethod,
                                          HttpServletRequest request,
                                          RedirectAttributes redirectAttributes) {
        // Process the form data (e.g., save the donation to a database or perform other actions)
        myFurnitureDonation = furnitureDonation;

        // For now, just adding the donation object to the model to display a confirmation
        model.addAttribute("donation", furnitureDonation);


        furnitureDonation.setHasCost(true);
        furnitureDonation.setCost(0);

        newdonationDetails = new DonationDetails();
        newdonationDetails.setDonationType(donationTypeService.saveDonationType(furnitureDonation));
        newdonationDetails.setQuantity(1); // Blood donations often don't have quantities
        FurnitureTruckFees furnitureTruckFees=new FurnitureTruckFees(donationDetailsService);
        newdonationDetails.setSubTotalPrice(furnitureTruckFees.calculate_price(newdonationDetails));
        newdonationDetails.setDonation_invoice_Description(furnitureTruckFees.display_invoice_details(newdonationDetails));
        // Return a confirmation view (could be a new page or the same page with a success message)
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
            redirectAttributes.addFlashAttribute("furnitureDonnation", myFurnitureDonation);
        }
        return "redirect:/getReceipt";
    }
}


