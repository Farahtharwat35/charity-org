package com.charity_org.demo.Models.Service;
import com.charity_org.demo.Enums.EventStatus;
import com.charity_org.demo.Models.Address;
import com.charity_org.demo.Models.Event;
import com.charity_org.demo.Models.repository.AddressRepository;
import com.charity_org.demo.Models.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
    public boolean createEvent(Event event) {
        eventRepository.save(event);
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

    public List<Event> listAllEvents() {
        return eventRepository.findAll().stream()
                .filter(event -> !event.isDeleted())
                .collect(Collectors.toList());
    }


    // Update event details
    public boolean update(Event updatedEvent) {
        Optional<Event> optionalEvent = eventRepository.findById(updatedEvent.getId());

        if (optionalEvent.isPresent()) {
            Event existingEvent = optionalEvent.get();

            // Update fields
            existingEvent.setEventName(updatedEvent.getEventName());
            existingEvent.setEventDate(updatedEvent.getEventDate());
            existingEvent.setEventLocationId(updatedEvent.getEventLocationId());
            existingEvent.setDescription(updatedEvent.getDescription());
            existingEvent.setStatus(updatedEvent.getStatus());

            // Save the updated event to the database
            eventRepository.save(existingEvent);
            return true;
        }

        // Event with given ID not found
        return false;
    }


    // Soft delete event by setting isDeleted to true
    public boolean deleteById(Long id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            event.setDeleted(true); // Set isDeleted to true instead of deleting the record
            eventRepository.save(event);
            return true;
        }
        return false;
    }

    // Soft delete event by setting isDeleted to true
    public Event getById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }


}
