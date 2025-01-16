package com.charity_org.demo.Models.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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

    private int quantity=1;

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
