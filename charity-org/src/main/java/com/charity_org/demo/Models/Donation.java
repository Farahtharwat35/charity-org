package com.charity_org.demo.Models;

import com.charity_org.demo.Enums.DonationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Getter
@Setter

public class Donation extends BaseEntity {

 @ManyToOne
 @JoinColumn(name = "userId")
 @Getter// Maps this field to the 'user' field in 'User'
 private User user;

 @OneToMany(mappedBy = "donation",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
 private List<DonationDetails> donationDetails;

 private Date date = new Date();
 private Time time = new Time(date.getTime());

 @Enumerated(EnumType.STRING)
 private DonationStatus status;

public void addTodonationDetials (DonationDetails d){
 this.donationDetails.add(d);
}
public Donation(){
 this.donationDetails = new ArrayList<DonationDetails>();
}

}