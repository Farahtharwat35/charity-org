package com.charity_org.demo.Models.Repository;
import com.charity_org.demo.Enums.DonationStatus;
import com.charity_org.demo.Models.Model.Donation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface DonationRepository extends JpaRepository<Donation, Long> {
    @Query("SELECT d FROM Donation d WHERE d.user.id = :userId")
    List<Donation> getDonationsByUserId(@Param("userId") long userId);

    @Query("SELECT COUNT(d) FROM Donation d")
    long countDonations();

    //update donation status

    @Modifying
    @Transactional
    @Query("UPDATE Donation d SET d.donationStatusClassName = :className WHERE d.id = :id")
    int updateDonationStatusById(@Param("id") Long id, @Param("className") String className);


    @Query("SELECT d FROM Donation d WHERE d.donationStatusClassName = 'com.charity_org.demo.Classes.State.PendingDonation'")
    List<Donation> findAllPendingDonations();
}