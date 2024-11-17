package com.charity_org.demo.Models.Service;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class DonationTotalPrice {
   private  double total_price;
   public abstract double calculate_price();
}
