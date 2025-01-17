package com.charity_org.demo.Classes.State;

import org.springframework.stereotype.Service;


public interface DonationStatus {
    public DonationStatus executeNext();
    String getDonationStatus();
    public DonationStatus cancelDonation();
}
