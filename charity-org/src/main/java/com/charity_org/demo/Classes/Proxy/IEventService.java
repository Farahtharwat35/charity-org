package com.charity_org.demo.Classes.Proxy;

import com.charity_org.demo.Models.Model.Event;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

public interface IEventService {
    boolean createEvent(String clientIp, String eventName, Date eventDate, long eventLocationId, String description);

    boolean createEvent(String clientIp, Event event);

    boolean updateEvent(String clientIp, long id, Event event);

    Event getEvent(long id);

    boolean deleteEvent(String clientIp, long id);

    List<Event> getAllEvents();

    List<Event> listAllEvents();


    boolean deleteById(String clientIp, Long id);
}
