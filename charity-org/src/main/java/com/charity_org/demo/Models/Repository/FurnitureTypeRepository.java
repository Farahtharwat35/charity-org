package com.charity_org.demo.Models.Repository;


import com.charity_org.demo.Models.Model.FurnitureType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FurnitureTypeRepository extends JpaRepository<FurnitureType, Long> {
    Optional<FurnitureType> findByName(String name);
}