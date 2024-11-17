package com.charity_org.demo.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paypal extends PaymentMethod{
    @Column(nullable = false)
    private String paypalEmail;
    @Column(nullable = false)
    private String password;


}
