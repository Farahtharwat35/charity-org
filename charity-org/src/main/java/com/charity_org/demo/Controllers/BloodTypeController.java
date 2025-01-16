package com.charity_org.demo.Controllers;


import com.charity_org.demo.Enums.BloodType;

import com.charity_org.demo.Models.Model.BloodDonnation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blood-type")
public class BloodTypeController {

    @GetMapping({"/", ""})
    public String getBloodPage(Model model) {
        // Add an empty BloodDonnation object to the model for form binding
        model.addAttribute("bloodDonnation", new BloodDonnation());
        // Add BloodType enum values for the dropdown
        model.addAttribute("bloodTypes", BloodType.values());
        return "BloodDonationView"; // View name for the form page
    }

    @PostMapping("/submitDonation")
    public String submitDonation(@ModelAttribute("bloodDonnation") BloodDonnation bloodDonnation) {
        // Log or process the submitted data
        System.out.println("Blood Donation Details:");
        System.out.println("Donator Age: " + bloodDonnation.getDonatorAge());
        System.out.println("Blood Type: " + bloodDonnation.getBloodType());
        System.out.println("Blood Amount: " + bloodDonnation.getBloodAmount());
        // Redirect or show success page
        return "redirect:/blood-type/success";
    }
}


