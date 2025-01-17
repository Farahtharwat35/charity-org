package com.charity_org.demo.Classes.State;

public class CanceledDonation implements DonationStatus {
    @Override
    public DonationStatus executeNext() {
        return new CanceledDonation();
    }

    @Override
    public String getDonationStatus() {
        return "Canceled";
    }

    @Override
    public DonationStatus cancelDonation() {
        return new CanceledDonation();
    }
}
