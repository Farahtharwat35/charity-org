package com.charity_org.demo.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Paypal extends BaseEntity {
    @Column(nullable = false)
    private String paypalEmail;
    @Column(nullable = false)
    private String password;
    public Paypal(String paypalEmail, String password){
        this.paypalEmail = paypalEmail;
        this.password = password;
    }


}
