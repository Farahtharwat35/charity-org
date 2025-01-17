package com.charity_org.demo.Models.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MoneyDonnation extends DonationType {
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;
}