package com.charity_org.demo.Models;

import com.charity_org.demo.Enums.Currencies;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MoneyDonnation extends DonationType {
private Currencies currency;




}
