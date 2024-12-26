package com.charity_org.demo.Classes.CommandComponents;

import com.charity_org.demo.Models.Model.Event;
import com.charity_org.demo.Models.Service.EventService;

public class CreateEventCommand implements EventCommand {
    private EventService eventService;
    private Event event;

    public CreateEventCommand(EventService eventService, Event event) {
        this.eventService = eventService;
        this.event = event;
    }

    @Override
    public void execute() {
        eventService.createEvent(event);
    }
}
