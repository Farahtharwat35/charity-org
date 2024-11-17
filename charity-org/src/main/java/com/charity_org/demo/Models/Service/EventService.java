package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Enums.EventStatus;
import com.charity_org.demo.Models.Address;
import com.charity_org.demo.Models.Event;
import com.charity_org.demo.Models.repository.AddressRepository;
import com.charity_org.demo.Models.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EventService implements IEventSubject {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private AddressRepository addressRepository;
    private ArrayList<IEventObserver> observers = new ArrayList<>();

    public boolean createEvent(String eventName, Date eventDate, long eventLocationId, String description) {
        Address eventAddress = addressRepository.getReferenceById(eventLocationId);  // Modify if needed to handle address creation

        if (eventAddress == null) {
            // If no address found, you could handle address creation here or throw an error
            eventAddress = new Address();
            addressRepository.save(eventAddress);
        }
        eventRepository.save(new Event(eventName, eventDate, addressRepository.getReferenceById(eventLocationId), description, EventStatus.UPCOMING));
        return true;
    }

    public boolean updateEvent(long Id, Event event) {
        event.setId(Id);
        eventRepository.save(event);
        return true;
    }

    ;

    public Event getEvent(long id) {
        return eventRepository.getReferenceById(id);
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
    public boolean notifyObserver(String subject, String content) {
        observers.forEach(observer -> {
            observer.sendNotification(subject, content);
        });
        return true;
    }
    public boolean deleteEvent(long id) {
        eventRepository.deleteById(id);
        return true;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
