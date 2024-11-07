package com.charity_org.demo.Models;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public abstract class Person extends BaseEntity {
    //private Donnation donnation;
    protected String name;
    protected int addressId ;
    protected String email;
    protected String password;
    protected int age;
}
