package com.charity_org.demo.Models.Repository;


import com.charity_org.demo.Models.Model.ClothesType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClothesTypeRepository extends JpaRepository<ClothesType, Long> {
    Optional<ClothesType> findByName(String name);
}