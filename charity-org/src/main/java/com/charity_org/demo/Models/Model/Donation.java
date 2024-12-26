package com.charity_org.demo.Models.Model;
import com.charity_org.demo.Enums.DonationStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Entity
@Data
public class Donation extends BaseEntity {

 @ManyToOne
 @JoinColumn(name = "userId")
 private User user;

 private Date date = new Date();
 private Time time = new Time(date.getTime());

 @Enumerated(EnumType.STRING)
 private DonationStatus status;

 private double donationTotalPrice;

 //private donnationdetails donationdetail;
}
