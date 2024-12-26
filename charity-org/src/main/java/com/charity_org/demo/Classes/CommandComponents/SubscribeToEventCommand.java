package com.charity_org.demo.Classes.CommandComponents;
import com.charity_org.demo.Classes.ObserverComponents.IEventObserver;
import com.charity_org.demo.Classes.ObserverComponents.IEventSubject;
import com.charity_org.demo.Models.Model.EventRegistration;
import com.charity_org.demo.Models.Service.EventRegistrationService;

public class SubscribeToEventCommand implements EventCommand {
    private IEventSubject eventSubject;
    private IEventObserver eventObserver;
    private EventRegistrationService eventRegistrationService;
    private EventRegistration eventRegistration;


    public SubscribeToEventCommand(IEventSubject eventSubject, IEventObserver eventObserver, String subject, String content, EventRegistrationService eventRegistrationService, EventRegistration eventRegistration) {
        this.eventSubject = eventSubject;
        this.eventObserver = eventObserver;
        this.eventRegistrationService = eventRegistrationService;
        this.eventRegistration = eventRegistration;
    }

    @Override
    public void execute() {
        eventSubject.addObserver(eventObserver);
        eventRegistrationService.register(eventRegistration);
    }

}
