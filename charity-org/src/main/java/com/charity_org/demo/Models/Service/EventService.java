package com.charity_org.demo.Models.Service;
import com.charity_org.demo.Classes.IteratorComponents.EventIterator;
import com.charity_org.demo.Classes.IteratorComponents.SearchEventIterator;

import com.charity_org.demo.Classes.ObserverComponents.IEventObserver;
import com.charity_org.demo.Classes.ObserverComponents.IEventSubject;
import com.charity_org.demo.Classes.Proxy.IEventService;
import com.charity_org.demo.Enums.EventStatus;
import com.charity_org.demo.Models.Model.Address;
import com.charity_org.demo.Models.Model.Event;
import com.charity_org.demo.Models.Repository.AddressRepository;
import com.charity_org.demo.Models.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

@Service
public class EventService implements IEventSubject, IEventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AddressRepository addressRepository;

    private ArrayList<IEventObserver> observers = new ArrayList<>();

    @Override
    public boolean createEvent(String clientIp, String eventName, Date eventDate, long eventLocationId, String description) {
        try {
            Address eventAddress = addressRepository.findById(eventLocationId).orElseThrow();
            Event event = new Event(eventName, eventDate, eventAddress, description, EventStatus.UPCOMING);
            eventRepository.save(event);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean createEvent(String clientIp, Event event) {
        try {
            eventRepository.save(event);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateEvent(String clientIp, long id, Event event) {
        event.setId(id);
        eventRepository.save(event);
        return true;
    }

    @Override
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
        observers.forEach(observer -> observer.sendNotification(subject, content));
        return true;
    }

    @Override
    public boolean deleteEvent(String clientIp, long id) {
        eventRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Event> getAllEvents(String clientIp, String queryString) {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> listAllUnDeletedEvents(String clientIp, String queryString) {
        return eventRepository.findAll().stream()
                .filter(event -> !event.isDeleted())
                .collect(Collectors.toList());
    }


    @Override
    public boolean deleteById(String clientIp, Long id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            event.setDeleted(true);
            eventRepository.save(event);
            return true;
        }
        return false;
    }
        // Update event details
        public boolean update(Event updatedEvent) {
            Optional<com.charity_org.demo.Models.Model.Event> optionalEvent = eventRepository.findById(updatedEvent.getId());

            if (optionalEvent.isPresent()) {
                com.charity_org.demo.Models.Model.Event existingEvent = optionalEvent.get();

                // Update fields
                existingEvent.setEventName(updatedEvent.getEventName());
                existingEvent.setEventDate(updatedEvent.getEventDate());
                existingEvent.setEventLocation(updatedEvent.getEventLocation());
                existingEvent.setDescription(updatedEvent.getDescription());
                existingEvent.setStatus(updatedEvent.getStatus());

                // Save the updated event to the database
                eventRepository.save(existingEvent);
                return true;
            }

            // Event with given ID not found
            return false;
        }

    public EventIterator createSearchIterator(String keyword) {
        List<Event> allEvents = listAllEvents(); // Get all events
        List<Event> matchingEvents = allEvents.stream()
                .filter(event -> event.getEventName().toLowerCase().contains(keyword.toLowerCase()) ||
                        event.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        return new SearchEventIterator(matchingEvents, keyword);
    }


    public Event getById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

}
