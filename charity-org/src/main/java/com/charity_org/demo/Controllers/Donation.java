package com.charity_org.demo.Controllers;

import com.charity_org.demo.Enums.Currencies;
import com.charity_org.demo.Models.DonnationDetails;
import com.charity_org.demo.Models.MoneyDonnation;
import com.charity_org.demo.Models.Service.DonationTypeService;
import com.charity_org.demo.Models.Service.Tax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
@RequestMapping("/donation")
public class Donation {
@Autowired DonationTypeService donationTypeService;
        @PostMapping("/money")
    public String handleMoneyDonation(
            @RequestParam Currencies currency,
            @RequestParam int contactInfo,
            @RequestParam(required = false) String specialInstructions,
            @RequestParam boolean hasCost,
            @RequestParam double cost,
            @RequestParam int quantity) {

        // Create MoneyDonation object
        MoneyDonnation moneyDonnation = new MoneyDonnation();
        moneyDonnation.setCurrency(currency);
        moneyDonnation.setContact_info(contactInfo);
        moneyDonnation.setSpecial_instructions(specialInstructions);
        moneyDonnation.setHasCost(true);
        moneyDonnation.setCost(cost);
            System.out.println(cost);
            System.out.println(moneyDonnation.getCost());

        DonnationDetails newdonationDetails= new DonnationDetails();
        newdonationDetails.setDonationType(donationTypeService.saveDonationType(moneyDonnation));
        newdonationDetails.setQuantity(quantity);
        newdonationDetails.setSubTotalPrice(newdonationDetails.calculate_price());
            Tax newTax = new Tax(newdonationDetails);
            newdonationDetails.setSubTotalPrice(newTax.calculate_price());
            System.out.println(newdonationDetails.getSubTotalPrice());

///**go to payment*/
        return "success";
    }
}
