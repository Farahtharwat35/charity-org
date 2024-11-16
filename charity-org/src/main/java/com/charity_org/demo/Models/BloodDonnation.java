package com.charity_org.demo.Models;


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
    String bloodType;
    double bloodAmount;



}
