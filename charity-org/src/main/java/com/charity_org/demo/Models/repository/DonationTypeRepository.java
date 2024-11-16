package com.charity_org.demo.Models.repository;

import com.charity_org.demo.Models.DonationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationTypeRepository extends JpaRepository<DonationType,Long> {
}
