package com.charity_org.demo.DTO;

import com.charity_org.demo.Enums.EventStatus;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class PostOrPutEventRequest {

    public interface Create {}

    @NotEmpty(message = "Event name is required.", groups = Create.class)
    @JsonProperty("event_name")
    private String eventName;

    @NotEmpty(message = "Event date is required.", groups = Create.class)
    @JsonProperty("event_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date eventDate;

    @NotEmpty(message = "Event location ID is required.", groups = Create.class)
    @JsonProperty("event_location_id")
    private long eventLocationId;

    @NotEmpty(message = "Event description is required.", groups = Create.class)
    @JsonProperty("description")
    private String description;

    private EventStatus eventStatus;

}
