package com.charity_org.demo.Models.Model;
import com.charity_org.demo.Enums.EventStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@Entity
@NoArgsConstructor
public class Event extends BaseEntity {

    @Column(nullable = false)
    private String eventName;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date eventDate;

    @ManyToOne
    @JoinColumn(name = "event_location_id")
    private Address eventLocation;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatus status = EventStatus.UPCOMING;


    public Event(String eventName, Date eventDate, Address eventLocationId, String description, EventStatus status) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventLocation = eventLocationId;
        this.description = description;
        this.status = status;
    }


}