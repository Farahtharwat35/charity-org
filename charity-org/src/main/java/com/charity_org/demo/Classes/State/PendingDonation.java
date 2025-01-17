package com.charity_org.demo.Classes.State;

public class PendingDonation implements DonationStatus {
    @Override
    public DonationStatus executeNext() {
    return new ShippedDonation();
    }

    @Override
    public String getDonationStatus() {
        return "Pending";
    }

    @Override
    public DonationStatus cancelDonation() {
        return new CanceledDonation();
    }
}
