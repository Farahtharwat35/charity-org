package com.charity_org.demo.Models.Model;

import com.charity_org.demo.Classes.State.CanceledDonation;
import com.charity_org.demo.Classes.State.CreatedDonation;
import com.charity_org.demo.Classes.State.DonationStatus;
import com.charity_org.demo.Models.Model.BaseEntity;
import com.charity_org.demo.Models.Model.DonationDetails;
import com.charity_org.demo.Models.Model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

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


 private double donationTotalPrice;


 private String donationStatusClassName;



 public void setDonationStatus(DonationStatus donationStatus) {
  this.donationStatus = donationStatus;
  this.donationStatusClassName = donationStatus.getClass().getName();
 }

 @Transient // JPA will not persist this field directly
 private DonationStatus donationStatus;

 @PostLoad // Runs after loading the entity from the database
 public void initializeDonationStatus() {
  try {
   if (donationStatusClassName != null) {
    Class<?> clazz = Class.forName(donationStatusClassName);
    this.donationStatus = (DonationStatus) clazz.getDeclaredConstructor().newInstance();
   }
  } catch (Exception e) {
   throw new RuntimeException("Error initializing donation status", e);
  }
 }

 public void addTodonationDetials (DonationDetails d){
  this.donationDetails.add(d);
  this.donationTotalPrice += d.getSubTotalPrice();
 }
 public Donation(){
  this.donationDetails = new ArrayList<DonationDetails>();
 }
 public void updateDonationStatus(){
  this.donationStatus = this.donationStatus.executeNext();
  this.donationStatusClassName = donationStatus.getClass().getName();

 }
 public String displayDonationStatus(){
  return this.donationStatus.getDonationStatus();
 }
 public void cancelDonation(){
  this.donationStatus = new CanceledDonation();
  this.donationStatusClassName = donationStatus.getClass().getName();
 }
}