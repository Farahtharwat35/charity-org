package com.charity_org.demo.Models;
import lombok.*;
import jakarta.persistence.Entity;
import com.charity_org.demo.Models.BaseEntity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity {
    private String name;
    private int parentID;

    public void updateById(String name, int parentID) {
        this.name = name;
        this.parentID = parentID;
    }
}

