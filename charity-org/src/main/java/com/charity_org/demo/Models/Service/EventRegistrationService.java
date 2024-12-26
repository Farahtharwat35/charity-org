package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Models.Model.Event;
import com.charity_org.demo.Models.Model.EventRegistration;
import com.charity_org.demo.Models.Repository.EventRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EventRegistrationService {
    @Autowired
    private EventRegistrationRepository EventRegistrationRepository;


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
}
