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
    private EventRegistrationRepository EventRegistrationRepository;

    public List<Event> getEventRegistredByUser(
            Long userID) {
        List<EventRegistration> registrations = EventRegistrationRepository.findRegistrationByUserID(userID);
        List<Event> events = new ArrayList<>() ;
        for (EventRegistration registration : registrations) {
            events.add(registration.getEvent());
        }
        return events;
    }

    public boolean register(EventRegistration eventRegistration) {
        if (!EventRegistrationRepository.findAll().contains(eventRegistration)) {
            EventRegistrationRepository.save(eventRegistration);

        }
        return true;
    }
    public boolean register(Event event, Date date) {
        if (!EventRegistrationRepository.findAll().contains(event)) {
            EventRegistration eventRegistration = new EventRegistration();
            eventRegistration.setEvent(event);
            eventRegistration.setRegisteredAt(date);
            EventRegistrationRepository.save(eventRegistration);
        }
        return true;}

    public boolean unregister(EventRegistration eventRegistration) {
        if (EventRegistrationRepository.findAll().contains(eventRegistration)) {
            EventRegistrationRepository.delete(eventRegistration);
        }
        return true;
    }
    public boolean isUserRegisteredForEvent(Long userId, Long eventId) {
        // This method should check the database to see if the user is already registered for the event.

        EventRegistration registration = EventRegistrationRepository.findByUserIdAndEventId(userId, eventId);
        return registration != null;
    }

}
