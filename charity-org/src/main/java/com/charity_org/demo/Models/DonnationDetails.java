package com.charity_org.demo.Models;


import com.charity_org.demo.Models.Service.DonationTotalPrice;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Getter
public class DonnationDetails extends DonationTotalPrice {
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "donationId")
    private Donation donation;



    @OneToOne(mappedBy = "donnationDetails")
    private DonationType donationType;

    private int quantity;

    private double subTotalPrice;


    @Override
    public double calculate_price() {
        return this.donationType.getCost() *this.quantity;
    }
}
