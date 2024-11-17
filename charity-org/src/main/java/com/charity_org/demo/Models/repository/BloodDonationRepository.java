package com.charity_org.demo.Models.repository;

import com.charity_org.demo.Models.BloodDonnation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodDonationRepository extends JpaRepository<BloodDonnation, Long> {
}
