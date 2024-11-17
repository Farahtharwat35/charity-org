package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Models.repository.DonationDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonationDetailsService extends DonationTotalPrice{
    @Autowired
    DonationDetailsRepository donationDetailsRepository;

    @Override
    public double calculate_price() {

        return 0;
    }
}
