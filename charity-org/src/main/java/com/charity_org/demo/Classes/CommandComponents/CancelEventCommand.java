package com.charity_org.demo.Classes.CommandComponents;

import com.charity_org.demo.Classes.ObserverComponents.IEventSubject;
import com.charity_org.demo.Models.Model.Event;
import com.charity_org.demo.Models.Service.EventService;

import javax.servlet.http.HttpServletRequest;

public class CancelEventCommand implements EventCommand {
    private IEventSubject eventSubject;
    private EventService eventService;
    private Event event;
    private String subject;
    private String content;
    private String clientIp;

    public CancelEventCommand(IEventSubject eventSubject, String subject, String content , EventService eventService , Event event, String clientIp) {
        this.eventSubject = eventSubject;
        this.subject = subject;
        this.content = content;
        this.eventService = eventService;
        this.event = event;
        this.clientIp = clientIp;
    }

    @Override
    public void execute() {
        eventSubject.notifyObserver(subject,content);
        eventService.deleteEvent(clientIp, event.getId());
    }

}
