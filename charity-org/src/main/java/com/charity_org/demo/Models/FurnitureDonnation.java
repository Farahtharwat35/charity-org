package com.charity_org.demo.Models;

import com.charity_org.demo.Enums.FurnitureCondition;
import com.charity_org.demo.Enums.FurnitureType;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FurnitureDonnation extends DonationType {

    FurnitureType FurnitureType;
    double weight;
    FurnitureCondition condition;
}
