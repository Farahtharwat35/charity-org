package com.charity_org.demo.Models.Service;
import com.charity_org.demo.Models.Donation;
import com.charity_org.demo.Models.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationService {
    @Autowired
    private DonationRepository donationRepository;

    public Donation save(Donation donation) {
        return donationRepository.save(donation);
    }

    public Donation getDonation(long id) {
        return donationRepository.getReferenceById(id);
    }
    public long getCount(){
        return donationRepository.countDonations();
    }

    public List<Donation> getDonationsByUserId(long userId){
        return donationRepository.getDonationsByUserId(userId);
    }

    public void updateDonationStatus(Long id, String status) {
        donationRepository.updateDonationStatusById(id, status);
    }

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }
}
