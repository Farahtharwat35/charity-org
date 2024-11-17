package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Models.DonationDetails;

public class ShippingFee extends TotalPriceDecorator{

    public ShippingFee(DonationTotalPrice donationTotalPrice) {
        super.donation_total_price = donationTotalPrice;
    }
    static double  Shipmentfee=50;

    @Override
    public String display_invoice_details(DonationDetails donationDetails) {
        return super.donation_total_price.display_invoice_details(donationDetails)+" +shipping cost: "+Shipmentfee+" ";
    }

    @Override
    public double calculate_price(DonationDetails donationDetails) {
        return super.donation_total_price.calculate_price(donationDetails)+Shipmentfee;
    }
}
