package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Models.ClothesDonnation;
import com.charity_org.demo.Models.repository.ClothesDonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClothesDonationService extends DonationTypeService{

    @Autowired
    public ClothesDonationRepository clothesDonationRepository;

    public ClothesDonnation save(ClothesDonnation clothesDonnation) {
        return clothesDonationRepository.save(clothesDonnation);
    }
}
