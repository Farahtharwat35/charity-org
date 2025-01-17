package com.charity_org.demo.Classes.State;

public class ShippedDonation implements DonationStatus {
    @Override
    public DonationStatus executeNext() {
        return new CompletedDonation();
    }

    @Override
    public String getDonationStatus() {
        return "The Donation Shipped";
    }
    @Override
    public DonationStatus cancelDonation() {
        return new CanceledDonation();
    }
}
