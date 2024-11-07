package com.charity_org.demo.Models;
import lombok.*;
import jakarta.persistence.Entity;


@Entity
@Data
public class Address extends BaseEntity {
    private String name;
    private int parentID;
}

