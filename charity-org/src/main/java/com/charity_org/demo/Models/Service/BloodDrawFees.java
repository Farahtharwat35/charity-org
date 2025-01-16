package com.charity_org.demo.Models.Service;


import com.charity_org.demo.Models.Model.DonationDetails;

public class BloodDrawFees extends TotalPriceDecorator{
    public BloodDrawFees(DonationTotalPrice donationTotalPrice) {
        super.donation_total_price = donationTotalPrice;
    }
    static double bloodDrawfee=250;

    @Override
    public String display_invoice_details(DonationDetails donationDetails) {
        return super.donation_total_price.display_invoice_details(donationDetails)+" +blood draw fees: "+bloodDrawfee+" ";
    }

    @Override
    public double calculate_price(DonationDetails donationDetails) {
        return super.donation_total_price.calculate_price(donationDetails)+bloodDrawfee;
    }
}
