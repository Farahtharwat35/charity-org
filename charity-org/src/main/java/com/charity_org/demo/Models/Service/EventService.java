package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Enums.EventStatus;
import com.charity_org.demo.Models.Event;
import com.charity_org.demo.Models.repository.AddressRepository;
import com.charity_org.demo.Models.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EventService implements IEventSubject{
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private AddressRepository addressRepository;
    private ArrayList<IEventObserver> observers = new ArrayList<>();

    public boolean createEvent(String eventName, Date eventDate, long eventLocationId, String description, EventStatus status) {
        eventRepository.save(new Event(eventName, eventDate, addressRepository.getReferenceById(eventLocationId) , description,status));
        return true;
    }

    @Override
    public boolean addObserver(IEventObserver observer) {
        if (observer == null)
            return false;

        observers.add(observer);
        return true;
    }

    @Override
    public boolean removeObserver(IEventObserver observer) {
        if (observer == null || !observers.contains(observer))
            return false;

        observers.remove(observer);
        return true;
    }

    @Override
    public boolean notifyObserver(String subject,String content) {
        observers.forEach(observer -> {
            observer.sendNotification(subject,content);
        });
        return true;
    }

//    public List<Event> getEventsParticipatedIn(long userId){
//        return eventRepository.getEventsParticipatedIn(userId);
//    }
}
