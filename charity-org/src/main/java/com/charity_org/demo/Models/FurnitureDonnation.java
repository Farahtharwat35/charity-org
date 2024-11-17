package com.charity_org.demo.Models;

import com.charity_org.demo.Enums.FurnitureCondition;
import com.charity_org.demo.Enums.FurnitureType;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FurnitureDonnation extends DonationType {

    FurnitureType FurnitureType;
    double weight;
    FurnitureCondition condition;
}
