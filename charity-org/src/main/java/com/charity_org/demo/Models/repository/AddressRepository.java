package com.charity_org.demo.Models.repository;

import com.charity_org.demo.Models.Address;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Address a SET a.name = :name WHERE a.id = :id")
    void updateAddressNameById(@Param("id") Long id, @Param("name") String name);
}