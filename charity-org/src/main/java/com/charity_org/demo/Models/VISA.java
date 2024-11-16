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
    private Integer cvv;

    @Column(nullable = false)
    private Date expirationDate;

    @Column(nullable = false)
    private String fName;

    @Column(nullable = false)
    private String middleName;

    @Column(nullable = false)
    private String lName;

    public VISA(){

    }
    public VISA(String cardNumber, Integer cvv, Date expirationDate, String fName, String middleName, String lName){
        this.cardNumber = cardNumber;
        this.cvv=cvv;
        this.expirationDate=expirationDate;
        this.fName=fName;
        this.middleName=middleName;
        this.lName=lName;

    }

}
