package com.charity_org.demo.Controllers;

import com.charity_org.demo.Enums.DonationStatus;
import com.charity_org.demo.Enums.FurnitureCondition;
import com.charity_org.demo.Enums.FurnitureType;
import com.charity_org.demo.Middlware.cookies.CookieHandler;
import com.charity_org.demo.Models.Model.Donation;
import com.charity_org.demo.Models.Model.DonationDetails;
import com.charity_org.demo.Models.Model.FurnitureDonnation;
import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Models.Service.DonationDetailsService;
import com.charity_org.demo.Models.Service.DonationService;
import com.charity_org.demo.Models.Service.DonationTypeService;
import com.charity_org.demo.Models.Service.FurnitureTruckFees;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    DonationDetails newdonationDetails;
    // Mapping for showing the furniture donation form
    @GetMapping({"", "/"})
    public String showFurnitureDonationForm(Model model) {
        // Create a new FurnitureDonation object to bind to the form
        model.addAttribute("furnitureDonation", new FurnitureDonnation());

        // Add the enums to the model so Thymeleaf can render them in the dropdowns
        List<FurnitureType> furnitureTypes = Arrays.asList(FurnitureType.values());
        List<FurnitureCondition> furnitureConditions = Arrays.asList(FurnitureCondition.values());

        model.addAttribute("furnitureTypes", furnitureTypes);
        model.addAttribute("furnitureConditions", furnitureConditions);

        return "FurnitureDonationView";  // Return the view name (furnitureDonationForm.html)
    }

    // Handle the form submission for furniture donation
    @PostMapping("/submitDonation")
    public String submitFurnitureDonation(@ModelAttribute("furnitureDonation") FurnitureDonnation furnitureDonation, Model model, @RequestParam("paymentMethod") String paymentMethod, HttpServletRequest request) {
        // Process the form data (e.g., save the donation to a database or perform other actions)

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


