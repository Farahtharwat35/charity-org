package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Models.DonationDetails;

public class Tax extends TotalPriceDecorator{

    public Tax(DonationTotalPrice donationTotalPrice) {
        super.donation_total_price = donationTotalPrice;
    }
    static double taxRate=0.12;

    @Override
    public String display_invoice_details(DonationDetails donationdetails) {
        return super.donation_total_price.display_invoice_details(donationdetails)+"+tax: "+taxRate*100;
    }

    @Override
    public double calculate_price(DonationDetails donationdetails) {
        return super.donation_total_price.calculate_price(donationdetails)*(1+taxRate);
    }
}
