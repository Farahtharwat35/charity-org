package com.charity_org.demo.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class EventRegistration extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "eventId")  // Maps this field to the 'user' field in 'User'
    private Event event;

    @Column(nullable = false)
    private Date registeredAt;

    @Column(nullable = false, length = 500)
    private String complains;

    public EventRegistration(){}

    public EventRegistration(User user, Event event, Date registeredAt, String complains){
        this.user = user;
        this.event = event;
        this.registeredAt = registeredAt;
        this.complains = complains;
    }
}
