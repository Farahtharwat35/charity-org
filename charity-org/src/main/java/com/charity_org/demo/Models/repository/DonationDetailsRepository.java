package com.charity_org.demo.Models.repository;

import com.charity_org.demo.Models.Donation;
import com.charity_org.demo.Models.DonnationDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationDetailsRepository extends JpaRepository<DonnationDetails, Long> {

}
