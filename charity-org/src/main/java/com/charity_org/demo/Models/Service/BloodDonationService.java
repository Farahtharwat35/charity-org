package com.charity_org.demo.Models.Service;


import com.charity_org.demo.Models.BloodDonnation;
import com.charity_org.demo.Models.DonationType;
import com.charity_org.demo.Models.repository.BloodDonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloodDonationService extends DonationType {
@Autowired
BloodDonationRepository bloodDonationRepository;

public BloodDonnation save(BloodDonnation bloodDonnation) {
    return bloodDonationRepository.save(bloodDonnation);
}
}
