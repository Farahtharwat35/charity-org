package com.charity_org.demo.Models.Service;


import com.charity_org.demo.Models.Model.DonationDetails;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class DonationTotalPrice {

   public abstract  String display_invoice_details(DonationDetails donationdetails);
   public abstract double calculate_price(DonationDetails donationdetails);
}
