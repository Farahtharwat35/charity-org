package com.charity_org.demo.Classes.State;

public class CreatedDonation implements DonationStatus{
    @Override
    public DonationStatus executeNext() {
    return new PendingDonation();
    }

    @Override
    public String getDonationStatus() {
    return "Created Donation";
    }

    @Override
    public DonationStatus cancelDonation() {
        return new CanceledDonation();
    }
}
