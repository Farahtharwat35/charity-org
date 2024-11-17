package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Models.DonationDetails;
import com.charity_org.demo.Models.DonationType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class DonationTotalPrice {

   public abstract  String display_invoice_details(DonationDetails donationdetails);
   public abstract double calculate_price(DonationDetails donationdetails);
}
