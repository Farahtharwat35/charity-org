package com.charity_org.demo.Models;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public abstract class Person extends EntityClass {
    //private Donnation donnation;
    private String name;
    private  int addressId ;
    private String email;
    private String password;
    private int age;
}
