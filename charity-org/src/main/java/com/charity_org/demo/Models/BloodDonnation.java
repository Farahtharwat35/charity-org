package com.charity_org.demo.Models;


import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BloodDonnation extends DonationType {

    int donatorAge;
    String bloodType;
    double bloodAmount;



}
