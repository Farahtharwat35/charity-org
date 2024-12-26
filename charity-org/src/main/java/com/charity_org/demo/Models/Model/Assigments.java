package com.charity_org.demo.Models.Model;

import com.charity_org.demo.Models.Model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Assigments extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "donation_id", nullable = false)
    private Donation donation;  // Foreign Key to Donation (One Donation -> One Assignment)

    // One courier can have many assignments, but each assignment has only one courier
    @ManyToOne
    @JoinColumn(name = "courier_id", nullable = false)
    private User courier;

    public Assigments(User user, Donation donation) {
        this.courier = user;
        this.donation = donation;
    }
}
