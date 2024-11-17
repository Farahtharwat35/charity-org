package com.charity_org.demo.Models.Service;

public class Tax extends TotalPriceDecorator{

    public Tax(DonationTotalPrice donationTotalPrice) {
        super.donation_total_price = donationTotalPrice;
    }
    static double taxRate=1.12;
    @Override
    public double calculate_price() {
        return super.donation_total_price.calculate_price()*taxRate;
    }
}
