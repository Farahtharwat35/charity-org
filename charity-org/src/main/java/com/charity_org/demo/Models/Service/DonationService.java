package com.charity_org.demo.Models.Service;
import com.charity_org.demo.Enums.DonationStatus;
import com.charity_org.demo.Models.Model.Donation;
import com.charity_org.demo.Models.Repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
//
    public void updateDonationStatus(Long id, String donationStatusClassName) {
        donationRepository.updateDonationStatusById(id, donationStatusClassName);
    }

    public List<Donation> getAllPendingDonations() {
        return donationRepository.findAllPendingDonations();
    }
}
