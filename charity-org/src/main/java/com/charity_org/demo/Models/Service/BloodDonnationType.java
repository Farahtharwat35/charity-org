package com.charity_org.demo.Models.Service;


import com.charity_org.demo.Models.DonationType;

public class BloodDonnationType extends DonationTypeService {
    @Override
    public DonationType saveDonationType(DonationType donationType) {
        return donationTypeRepository.save(donationType);
    }
}
