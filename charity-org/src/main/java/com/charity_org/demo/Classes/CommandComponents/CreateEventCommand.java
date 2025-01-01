package com.charity_org.demo.Classes.CommandComponents;

import com.charity_org.demo.Models.Model.Event;
import com.charity_org.demo.Models.Service.EventService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class CreateEventCommand implements EventCommand {
    private final EventService eventService;
    private final String clientIp;
    private final String eventName;
    private final Date eventDate;
    private final long eventLocationId;
    private final String description;


    public CreateEventCommand(EventService eventService, String eventName, Date eventDate, long eventLocationId, String description, String clientIp) {
        this.eventService = eventService;
        this.clientIp = clientIp;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventLocationId = eventLocationId;
        this.description = description;
    }

    @Override
    public boolean execute() {
        return eventService.createEvent(clientIp,eventName, eventDate, eventLocationId, description);
    }
}
