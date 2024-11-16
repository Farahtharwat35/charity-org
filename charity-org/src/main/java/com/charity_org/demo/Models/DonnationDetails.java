package com.charity_org.demo.Models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class DonnationDetails extends BaseEntity{
    @OneToOne
    @JoinColumn(name = "donationId")
    private Donation donationId;



    @OneToMany(mappedBy = "donnationDetails")
    private List<DonationType> donationTypes;

    private int quantity;

    private double subTotalPrice;




}
