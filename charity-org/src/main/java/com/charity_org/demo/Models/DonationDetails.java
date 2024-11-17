package com.charity_org.demo.Models;


import com.charity_org.demo.Models.Service.DonationTotalPrice;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Getter
public class DonationDetails extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "donationId")
    private Donation donation;



    @OneToOne(mappedBy = "donationDetails")
    private DonationType donationType;

    private int quantity;

    private double subTotalPrice;

    private String donation_invoice_Description;

//    @Override
//    public String display_invoice_details() {
//        return "cost: "+this.donationType.getCost()+" ";
//    }

//    @Override
//    public double calculate_price() {
//        return this.donationType.getCost() *this.quantity;
//    }
}
