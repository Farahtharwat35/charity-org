package com.charity_org.demo.Classes.Proxy;

import com.charity_org.demo.Models.Model.Event;

import java.util.Date;
import java.util.List;

public interface IEventService {
    boolean createEvent(String clientIp, String eventName, Date eventDate, long eventLocationId, String description);

    boolean updateEvent(String clientIp, long id, Event event);

    Event getEvent(long id);

    boolean deleteEvent(String clientIp, long id);

    List<Event> getAllEvents(String clientIp, String queryString);

    List<Event> listAllUnDeletedEvents(String clientIp, String queryString);

    boolean deleteById(String clientIp, Long id);
}
