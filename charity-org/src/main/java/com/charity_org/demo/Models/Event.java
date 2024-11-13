package com.charity_org.demo.Models;

import com.charity_org.demo.Enums.EventStatus;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Data
@Entity
public class Event extends BaseEntity{

    @Column(nullable = false)
    private String eventName;

    @Column(nullable = false)
    private Date eventDate;

    @Column(nullable = false)
    private int eventLocationId;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    private EventStatus status;

    public Event(){

    }

    public Event(String eventName, Date eventDate, int eventLocationId, String description, EventStatus status) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventLocationId = eventLocationId;
        this.description = description;
        this.status = status;
    }

}