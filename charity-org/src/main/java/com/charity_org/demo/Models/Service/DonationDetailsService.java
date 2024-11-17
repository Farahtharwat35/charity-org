package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Models.DonationDetails;
import com.charity_org.demo.Models.DonationType;
import com.charity_org.demo.Models.repository.DonationDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonationDetailsService extends DonationTotalPrice{
    @Autowired
    DonationDetailsRepository donationDetailsRepository;
    public DonationDetails saveDonationDetails(DonationDetails donationDetails){
        return donationDetailsRepository.save(donationDetails);
    }

    @Override
    public double calculate_price(DonationDetails donationdetails) {
        return donationdetails.getDonationType().getCost() *donationdetails.getQuantity();
    }

    @Override
    public String display_invoice_details(DonationDetails donationdetails) {
        return "cost: "+donationdetails.getDonationType().getCost()+" ";
    }

}
