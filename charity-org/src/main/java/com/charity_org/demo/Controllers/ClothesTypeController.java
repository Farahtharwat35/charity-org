package com.charity_org.demo.Controllers;

import com.charity_org.demo.Enums.ClothesSize;
import com.charity_org.demo.Enums.ClothesType;
import com.charity_org.demo.Enums.Season;
import com.charity_org.demo.Models.ClothesDonnation;
import com.charity_org.demo.Models.Service.ClothesDonnationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clothes-type")
public class ClothesTypeController {

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

    @PostMapping("/clothes-donation/submitDonation")
    public String submitDonation(@ModelAttribute ClothesDonnation clothesDonnation) {
        // Save the clothesDonnation to the database or process it as required
        return "donation-success";
    }
}
