package com.charity_org.demo.Models.Repository;


import com.charity_org.demo.Models.Model.DonationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationTypeRepository extends JpaRepository<DonationType,Long> {
}
