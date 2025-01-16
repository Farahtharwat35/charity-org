package com.charity_org.demo.Models.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public abstract class DonationType extends BaseEntity{
    @OneToOne
    @JoinColumn(name = "donationDetailsID")
    private DonationDetails donationDetails;

    private int contact_info;
    private String special_instructions;
    private boolean hasCost;
    private double cost=0;

}
