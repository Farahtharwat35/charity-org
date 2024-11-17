package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Models.FurnitureDonnation;
import com.charity_org.demo.Models.repository.FurnitureDonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FurnitureDonationService extends DonationTypeService {

@Autowired
    FurnitureDonationRepository furnitureDonationRepository;

public FurnitureDonnation save(FurnitureDonnation furnitureDonnation) {
    return furnitureDonationRepository.save(furnitureDonnation);
}

}
