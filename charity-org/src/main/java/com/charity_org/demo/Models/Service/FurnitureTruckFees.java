package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Models.DonationDetails;

public class FurnitureTruckFees extends TotalPriceDecorator{
    public FurnitureTruckFees(DonationTotalPrice donationTotalPrice) {
        super.donation_total_price = donationTotalPrice;
    }
    static double truckprice=500;

    @Override
    public String display_invoice_details(DonationDetails donationDetails) {
        return super.donation_total_price.display_invoice_details(donationDetails)+" +truck price:"+truckprice+" ";
    }

    @Override
    public double calculate_price(DonationDetails donationDetails) {
        return super.donation_total_price.calculate_price(donationDetails)+truckprice;
    }
}
