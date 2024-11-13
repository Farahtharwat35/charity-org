package com.charity_org.demo.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class VISA extends BaseEntity {

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private int cvv;

    @Column(nullable = false)
    private Date expirationDate;

    @Column(nullable = false)
    private String fName;

    @Column(nullable = false)
    private String middleName;

    @Column(nullable = false)
    private String lName;


}
