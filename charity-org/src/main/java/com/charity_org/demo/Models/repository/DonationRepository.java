package com.charity_org.demo.Models.repository;

import com.charity_org.demo.Models.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface DonationRepository extends JpaRepository<Donation, Long> {
    @Query("SELECT d FROM Donation d WHERE d.userId = :userId")
    Donation[] getDonationsByUserId(@Param("userId") long userId);

    @Query("SELECT COUNT(d) FROM Donation d")
    long countDonations();
}