package com.charity_org.demo.Classes.State;

public class CompletedDonation implements DonationStatus {
    @Override
    public DonationStatus executeNext() {
        return new CompletedDonation();
    }

    @Override
    public String getDonationStatus() {
    return "Completed";
    }

    @Override
    public DonationStatus cancelDonation() {
        return new CanceledDonation();
    }
}
