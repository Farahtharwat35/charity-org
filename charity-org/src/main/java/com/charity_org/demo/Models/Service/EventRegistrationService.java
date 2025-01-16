package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Models.Model.Event;
import com.charity_org.demo.Models.Model.EventRegistration;
import com.charity_org.demo.Models.Repository.EventRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EventRegistrationService {

    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;

    public List<Event> getEventRegistredByUser(
            Long userID) {
        List<EventRegistration> registrations = eventRegistrationRepository.findRegistrationByUserID(userID);
        List<Event> events = new ArrayList<>() ;
        for (EventRegistration registration : registrations) {
            events.add(registration.getEvent());
        }
        return events;
    }

    public boolean register(EventRegistration eventRegistration) {
        if (!eventRegistrationRepository.findAll().contains(eventRegistration)) {
            eventRegistrationRepository.save(eventRegistration);

        }
        return true;
    }

    public boolean unregister(Long registrationID) {
        return eventRegistrationRepository.deleteEventRegistration(registrationID)>0;
    }
    public EventRegistration isUserRegisteredForEvent(Long userId, Long eventId) {
        return eventRegistrationRepository.findByUserIdAndEventId(userId, eventId);
    }

}
