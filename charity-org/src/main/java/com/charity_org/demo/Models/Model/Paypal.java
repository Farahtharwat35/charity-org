package com.charity_org.demo.Models.Model;


import com.charity_org.demo.Classes.StrategyComponents.IPaymentMethod;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paypal extends BaseEntity implements IPaymentMethod {
    @Column(nullable = false)
    private String paypalEmail;
    @Column(nullable = false)
    private String password;


}
