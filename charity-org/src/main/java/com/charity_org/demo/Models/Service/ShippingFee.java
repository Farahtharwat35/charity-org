package com.charity_org.demo.Models.Service;

public class ShippingFee extends TotalPriceDecorator{

    public ShippingFee(DonationTotalPrice donationTotalPrice) {
        super.donation_total_price = donationTotalPrice;
    }
    static double  Shipmentfee=50;
    @Override
    public double calculate_price() {
        return super.donation_total_price.calculate_price()+Shipmentfee;
    }
}
