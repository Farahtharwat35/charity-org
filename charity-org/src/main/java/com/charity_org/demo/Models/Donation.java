package com.charity_org.demo.Models;
import jakarta.persistence.*;
import lombok.Data;
import java.sql.Time;
import java.util.Date;

@Entity
@Data
public class Donation extends BaseEntity {

 @Column(nullable = false)
 private long userId;
 private Date date= new Date();
 private Time time = new Time(date.getTime());

 @Enumerated(EnumType.STRING)
 private String donationStatus;

 private double donationTotalPrice;
 //private donnationdetails donationdetail;

}
