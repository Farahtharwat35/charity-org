package com.charity_org.demo.Models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public abstract class DonationType extends  BaseEntity{

    private String title;
    private String description;
    private boolean hasCost;
    private double cost;

}
