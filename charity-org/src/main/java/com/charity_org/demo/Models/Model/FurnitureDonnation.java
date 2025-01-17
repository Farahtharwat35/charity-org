package com.charity_org.demo.Models.Model;

import com.charity_org.demo.Enums.FurnitureCondition;
import com.charity_org.demo.Models.Model.FurnitureType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FurnitureDonnation extends DonationType {

    @ManyToOne
    @JoinColumn(name = "furniture_type_id")
    private FurnitureType furnitureType;
    double weight;
    FurnitureCondition condition;
}
