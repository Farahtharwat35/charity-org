package com.charity_org.demo.Models.Service;


import com.charity_org.demo.Models.Model.DonationType;
import com.charity_org.demo.Models.Repository.DonationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonationTypeService {
    @Autowired
    DonationTypeRepository donationTypeRepository;

    public DonationType saveDonationType(DonationType donationType) {
        return donationTypeRepository.save(donationType);
    }
}
