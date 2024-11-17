package com.charity_org.demo.Models;

import com.charity_org.demo.Enums.Currencies;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyDonnation  extends DonationType {
private Currencies currency;




}
