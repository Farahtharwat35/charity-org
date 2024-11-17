package com.charity_org.demo.Models;


import com.charity_org.demo.Enums.BloodType;
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
    BloodType bloodType;
    double bloodAmount;



}
