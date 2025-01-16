package com.charity_org.demo.Controllers;

import com.charity_org.demo.Enums.FurnitureCondition;
import com.charity_org.demo.Enums.FurnitureType;
import com.charity_org.demo.Models.Model.FurnitureDonnation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/furniture-type")
public class FurnitureTypeController {
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
    public String submitFurnitureDonation(@ModelAttribute("furnitureDonation") FurnitureDonnation furnitureDonation, Model model) {
        // Process the form data (e.g., save the donation to a database or perform other actions)

        // For now, just adding the donation object to the model to display a confirmation
        model.addAttribute("donation", furnitureDonation);

        // Return a confirmation view (could be a new page or the same page with a success message)
        return "donationConfirmation";  // Return the confirmation view
    }
}
