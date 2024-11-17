package com.charity_org.demo.Models;


import com.charity_org.demo.Enums.BloodType;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodDonnation extends DonationType {

    int donatorAge;
    BloodType bloodType;
    double bloodAmount;



}
