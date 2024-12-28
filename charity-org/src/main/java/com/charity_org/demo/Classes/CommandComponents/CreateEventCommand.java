package com.charity_org.demo.Classes.CommandComponents;

import com.charity_org.demo.Models.Model.Event;
import com.charity_org.demo.Models.Service.EventService;

import javax.servlet.http.HttpServletRequest;

public class CreateEventCommand implements EventCommand {
    private final EventService eventService;
    private final Event event;
    private final String clientIp;

    public CreateEventCommand(EventService eventService, Event event, String clientIp) {
        this.eventService = eventService;
        this.event = event;
        this.clientIp = clientIp;
    }

    @Override
    public void execute() {
        eventService.createEvent(clientIp, event);
    }
}
