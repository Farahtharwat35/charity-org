package com.charity_org.demo.Controllers;

import com.charity_org.demo.Enums.*;


import com.charity_org.demo.Middlware.cookies.CookieHandler;
import com.charity_org.demo.Middlware.cookies.SessionRepository;
import com.charity_org.demo.Models.Model.*;
import com.charity_org.demo.Models.Model.MoneyDonnation;
import com.charity_org.demo.Models.Service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/donation-types")
public class DonationTypesController {
    @Autowired
    DonationTypeService donationTypeService;

    @Autowired
    DonationDetailsService donationDetailsService;

    @Autowired
    DonationService donationService;

    @Autowired
    CookieHandler cookieHandler;

    @Autowired
    SessionRepository sessionRepository;

    @GetMapping("/")
    public String viewAllTypes()
    {
        return "ListDonationTypesView";
    }

    @GetMapping("/furniture")
    public String getFurniturePage(){
        return "x";
    }

    @GetMapping("/money")
    public String getMoneyPage(){
            return "MoneyDonationView";
    }

    @GetMapping("/clothes")
    public String getClothesPage(){
        return "x";
    }

    @GetMapping("/blood")
    public String getBloodPage(){
        return "BloodDonationView";
    }

}
