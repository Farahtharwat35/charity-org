package com.charity_org.demo.Controllers;

import com.charity_org.demo.Enums.EventStatus;
import com.charity_org.demo.Models.Event;
import com.charity_org.demo.Models.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/events") // This adds an initial path to all endpoints in this controller
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping("/get-all")
    public List<Event> getEvents() {
        return eventService.listAllEvents();
    }

    @GetMapping("/create")
    public long createEvent(){
        eventService.createEvent("test",new Date(),0,"HHHH", EventStatus.UPCOMING);
        return eventService.listAllEvents().size();
    }

    // Update event endpoint
    @PutMapping("/update/{id}")
    public boolean updateEvent(@PathVariable Long id,
                               @RequestParam String eventName,
                               @RequestParam Date eventDate,
                               @RequestParam int eventLocationId,
                               @RequestParam String description,
                               @RequestParam EventStatus status) {
        return eventService.update(id, eventName, eventDate, eventLocationId, description, status);
    }

    // Delete event endpoint
    @DeleteMapping("/delete/{id}")
    public boolean deleteEvent(@PathVariable Long id) {
        return eventService.deleteById(id);
    }


    // Delete event endpoint
    @GetMapping("/get/{id}")
    public Event getById(@PathVariable Long id) {
        return eventService.getById(id);
    }
}
