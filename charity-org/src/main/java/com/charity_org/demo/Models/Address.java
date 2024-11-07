package com.charity_org.demo.Models;
import lombok.*;
import jakarta.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
public class Address {
    private String name;
    private int parentID;

    public void updateById(String name, int parentID) {
        this.name = name;
        this.parentID = parentID;
    }
}

