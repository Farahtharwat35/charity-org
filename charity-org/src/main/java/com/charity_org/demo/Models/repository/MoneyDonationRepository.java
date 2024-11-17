package com.charity_org.demo.Models.repository;

import com.charity_org.demo.Models.MoneyDonnation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyDonationRepository extends JpaRepository<MoneyDonnation, Long> {
}
