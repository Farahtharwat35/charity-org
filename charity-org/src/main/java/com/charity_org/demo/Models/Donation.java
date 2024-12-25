package com.charity_org.demo.Models;

import com.charity_org.demo.Enums.DonationStatus;
import com.charity_org.demo.Enums.Roles;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.util.Date;
import java.util.List;

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
