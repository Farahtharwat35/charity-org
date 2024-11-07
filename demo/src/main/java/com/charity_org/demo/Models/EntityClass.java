package com.charity_org.demo.Models;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@jakarta.persistence.Entity
@Data

public abstract class EntityClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean isDeleted;
//    private DBconnection dbConnection ;
}
