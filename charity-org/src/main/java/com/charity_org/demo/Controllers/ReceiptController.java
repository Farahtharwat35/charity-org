package com.charity_org.demo.Controllers;

import com.charity_org.demo.Classes.Factory.ReceiptFactory;
import com.charity_org.demo.Middlware.cookies.CookieHandler;
import com.charity_org.demo.Models.Model.*;
import com.charity_org.demo.Models.Service.DonationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/getReceipt")
public class ReceiptController {
    ReceiptFactory receiptFactory;

    @Autowired
    CookieHandler cookieHandler;

    @Autowired
    DonationService donationService;

    @GetMapping({"", "/"})
    public String showReceipt(@RequestHeader(value = "Referer", required = false) String referer,
                              HttpServletRequest request, Model model,
                              @ModelAttribute("donationDetails") DonationDetails donationDetails,
                              @ModelAttribute("moneyDonation") MoneyDonnation moneyDonation,
                              @ModelAttribute("furnitureDonnation") FurnitureDonnation furnitureDonnation,
                              @ModelAttribute("bloodDonnation") BloodDonnation bloodDonnation,
                              @ModelAttribute("clothesDonnation") ClothesDonnation clothesDonnation){
        if(referer == null){
            return "redirect:/donation-types/";
        }
        String path = referer.replace("http://localhost:8080/", "");
        // Get the first segment of the path
        String firstPath = path.split("/")[0];
        receiptFactory = new ReceiptFactory();

        // Get the current user from the session or cookie
        User currentUser = cookieHandler.getUserFromSession(request);

        // Add User and Donation to the model
        model.addAttribute("user", currentUser);
        model.addAttribute("donationDetails", donationDetails);
        model.addAttribute("moneyDonnation", moneyDonation);
        model.addAttribute("furnitureDonnation", furnitureDonnation);
        model.addAttribute("bloodDonnation", bloodDonnation);
        model.addAttribute("clothesDonnation", clothesDonnation);

        return receiptFactory.createView(firstPath);
    }
}
