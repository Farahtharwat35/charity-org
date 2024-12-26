package com.charity_org.demo.Classes.CommandComponents;

import com.charity_org.demo.Classes.ObserverComponents.IEventObserver;
import com.charity_org.demo.Classes.ObserverComponents.IEventSubject;
import com.charity_org.demo.Models.Model.EventRegistration;
import com.charity_org.demo.Models.Service.EventRegistrationService;

public class UnsubscribeToEventCommand implements EventCommand {
    private final IEventSubject eventSubject;
    private final IEventObserver eventObserver;
    private final EventRegistrationService eventRegistrationService;
    private final EventRegistration eventRegistration;

    public UnsubscribeToEventCommand(IEventSubject eventSubject, IEventObserver eventObserver, EventRegistrationService eventRegistrationService, EventRegistration eventRegistration) {
        this.eventSubject = eventSubject;
        this.eventObserver = eventObserver;
        this.eventRegistrationService = eventRegistrationService;
        this.eventRegistration = eventRegistration;
    }

    @Override
    public void execute() {
        eventSubject.removeObserver(eventObserver);
        eventRegistrationService.unregister(eventRegistration);
    }
}
