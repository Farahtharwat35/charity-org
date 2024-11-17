package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Models.DonationType;
import com.charity_org.demo.Models.MoneyDonnation;
import com.charity_org.demo.Models.repository.MoneyDonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoneyDonationService extends DonationTypeService {
    @Autowired
    MoneyDonationRepository moneyDonationRepository;


public MoneyDonnation save(MoneyDonnation moneyDonnation) {
    return moneyDonationRepository.save(moneyDonnation);
}
}
