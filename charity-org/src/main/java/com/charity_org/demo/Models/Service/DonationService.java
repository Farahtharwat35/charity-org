package com.charity_org.demo.Models.Service;
import com.charity_org.demo.Models.Donation;
import com.charity_org.demo.Models.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Donation[] getDonationsByUserId(long userId){
        return donationRepository.getDonationsByUserId(userId);
    }
}
