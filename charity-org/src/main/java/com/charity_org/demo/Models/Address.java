package com.charity_org.demo.Models;
import lombok.*;
import jakarta.*;

@Entity
@Data
@AllArgsConstructor
public class Address extends Entity {
    private String name;
    private int parentID;

    public updateById(name:String, parentID:int){
        this.name = name;
        this.parentID = parentID;
    }

}