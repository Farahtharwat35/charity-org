package com.charity_org.demo.Models.Model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Entity
public class EventRegistration extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "eventId")
    private Event event;

    @Column(nullable = false)
    private Date registeredAt;

    @Column(length = 500)
    private String complains;

    public EventRegistration(){}

    public EventRegistration(User user, Event event, Date registeredAt, String complains){
        this.user = user;
        this.event = event;
        this.registeredAt = registeredAt;
        this.complains = complains;
    }
}
