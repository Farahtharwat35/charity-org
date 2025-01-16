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

    @PostMapping("/money")
    public String handleMoneyDonation(
            @RequestParam Currencies currency,
            @RequestParam int contactInfo,
            @RequestParam(required = false) String specialInstructions,
            @RequestParam boolean hasCost,
            @RequestParam double cost,
            @RequestParam int quantity,
            HttpServletRequest request) {

        // Create MoneyDonation object
        MoneyDonnation moneyDonnation = new MoneyDonnation();
        moneyDonnation.setCurrency(currency);
        moneyDonnation.setContact_info(contactInfo);
        moneyDonnation.setSpecial_instructions(specialInstructions);
        moneyDonnation.setHasCost(true);
        moneyDonnation.setCost(cost);
        System.out.println(cost);
        System.out.println(moneyDonnation.getCost());

        DonationDetails newdonationDetails= new DonationDetails();
        newdonationDetails.setDonationType(donationTypeService.saveDonationType(moneyDonnation));
        newdonationDetails.setQuantity(quantity);

        Tax newTax = new Tax(donationDetailsService);
        newdonationDetails.setSubTotalPrice(newTax.calculate_price(newdonationDetails));
        newdonationDetails.setDonation_invoice_Description(newTax.display_invoice_details(newdonationDetails));
        System.out.println(newdonationDetails.getDonation_invoice_Description());
        newdonationDetails.getSubTotalPrice();

        if (true){//here put the condition
            Donation donation = new Donation();
            User currentUser = cookieHandler.getUserFromSession(request);
            donation.setUser(currentUser);
            donation.addTodonationDetials(donationDetailsService.saveDonationDetails(newdonationDetails));
            newdonationDetails.setDonation(donation);
            donationService.save(donation);
        }
        System.out.println(newdonationDetails.getSubTotalPrice());

        ///**go to payment*/

        return "success";
    }

    // Clothes Donation
    @PostMapping("/clothes")
    public String handleClothesDonation(
            @RequestParam ClothesType clothingType,
            @RequestParam ClothesSize size,
            @RequestParam int quantity,
            @RequestParam Season season,
            @RequestParam int contactInfo,
            @RequestParam(required = false) String specialInstructions,
            HttpServletRequest request) {

        ClothesDonnation clothesDonation = new ClothesDonnation();
        clothesDonation.setClothingType(clothingType);
        clothesDonation.setSize(size);
        clothesDonation.setQuantity(quantity);
        clothesDonation.setSeason(season);
        clothesDonation.setContact_info(contactInfo);
        clothesDonation.setSpecial_instructions(specialInstructions);
        clothesDonation.setHasCost(false);
        clothesDonation.setCost(0);

        DonationDetails donationDetails = new DonationDetails();
        donationDetails.setDonationType(donationTypeService.saveDonationType(clothesDonation));
        donationDetails.setQuantity(quantity);
        ShippingFee shippingFee = new ShippingFee(donationDetailsService);
        donationDetails.setSubTotalPrice(shippingFee.calculate_price(donationDetails));
        donationDetails.setDonation_invoice_Description(shippingFee.display_invoice_details(donationDetails));
        if (true){//here put the condition

            Donation donation = new Donation();
            User currentUser = cookieHandler.getUserFromSession(request);
            donation.setUser(currentUser);
            donation.addTodonationDetials(donationDetailsService.saveDonationDetails(donationDetails));
            donationDetails.setDonation(donation);
            donationService.save(donation);
        }
        return "Clothes donation saved successfully with ID: " + donationDetails.getId();
    }

    // Blood Donation
    @PostMapping("/blood")
    public String handleBloodDonation(
            @RequestParam int donatorAge,
            @RequestParam BloodType bloodType,
            @RequestParam double bloodAmount,
            @RequestParam int contactInfo,
            @RequestParam(required = false) String specialInstructions
            ,HttpServletRequest request

            ) {

        BloodDonnation bloodDonation = new BloodDonnation();
        bloodDonation.setDonatorAge(donatorAge);
        bloodDonation.setBloodType(bloodType);
        bloodDonation.setBloodAmount(bloodAmount);
        bloodDonation.setContact_info(contactInfo);
        bloodDonation.setSpecial_instructions(specialInstructions);
        bloodDonation.setHasCost(false);
        bloodDonation.setCost(0);

        DonationDetails donationDetails = new DonationDetails();
        donationDetails.setDonationType(donationTypeService.saveDonationType(bloodDonation));
        donationDetails.setQuantity(1); // Blood donations often don't have quantities
        ShippingFee shippingFee = new ShippingFee(donationDetailsService);
        BloodDrawFees newBloodDrawFees = new BloodDrawFees(shippingFee);
        donationDetails.setSubTotalPrice(newBloodDrawFees.calculate_price(donationDetails));
        donationDetails.setDonation_invoice_Description(newBloodDrawFees.display_invoice_details(donationDetails));
        System.out.println(donationDetails.getSubTotalPrice());
        System.out.println(donationDetails.getDonation_invoice_Description());
        if (true){//here put the condition
            Donation donation = new Donation();
            User currentUser = cookieHandler.getUserFromSession(request);
            donation.setUser(currentUser);
            donation.addTodonationDetials(donationDetailsService.saveDonationDetails(donationDetails));
            donationDetails.setDonation(donation);
            donationService.save(donation);
        }
        return "Blood donation saved successfully with ID: " + donationDetails.getId();

    }

//    // Furniture Donation
    @PostMapping("/furniture")
    public String handleFurnitureDonation(
            @RequestParam FurnitureType furnitureType,
            @RequestParam double weight,
            @RequestParam FurnitureCondition condition,
            @RequestParam int contactInfo,
            @RequestParam(required = false) String specialInstructions,
            @RequestParam int quantity,
            HttpServletRequest request) {

        FurnitureDonnation furnitureDonation = new FurnitureDonnation();
        furnitureDonation.setFurnitureType(furnitureType);
        furnitureDonation.setWeight(weight);
        furnitureDonation.setCondition(condition);
        furnitureDonation.setContact_info(contactInfo);
        furnitureDonation.setSpecial_instructions(specialInstructions);
        furnitureDonation.setHasCost(true);
        furnitureDonation.setCost(0);

        DonationDetails donationDetails = new DonationDetails();
        donationDetails.setDonationType(donationTypeService.saveDonationType(furnitureDonation));
        donationDetails.setQuantity(1); // Blood donations often don't have quantities
        FurnitureTruckFees furnitureTruckFees=new FurnitureTruckFees(donationDetailsService);
        donationDetails.setSubTotalPrice(furnitureTruckFees.calculate_price(donationDetails));
        donationDetails.setDonation_invoice_Description(furnitureTruckFees.display_invoice_details(donationDetails));
        if (true){//here put the condition

            Donation donation = new Donation();
            User currentUser = cookieHandler.getUserFromSession(request);
            donation.setUser(currentUser);
            donation.addTodonationDetials(donationDetailsService.saveDonationDetails(donationDetails));
            donationDetails.setDonation(donation);
            donationService.save(donation);
        }
        return "Furniture donation saved successfully with ID: " + donationDetails.getId();
    }
}
