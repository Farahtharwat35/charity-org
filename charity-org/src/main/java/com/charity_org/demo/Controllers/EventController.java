package com.charity_org.demo.Controllers;


import com.charity_org.demo.Enums.EventStatus;
import com.charity_org.demo.Models.Event;
import com.charity_org.demo.Models.EventRegistration;
import com.charity_org.demo.Models.Service.EventRegistrationService;
import com.charity_org.demo.Models.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/events") // This adds an initial path to all endpoints in this controller
public class EventController {
    @Autowired
    private EventService eventService;

    @Autowired
    private EventRegistrationService eventRegistrationService;

    @GetMapping("/get-all")
    public String getEvents(Model model) {
        List<Event> events = eventService.listAllEvents();
        model.addAttribute("events", events);
        return "ListEventsView";
    }



    @GetMapping("/create")
    public long createEvent(@RequestParam String eventName,
                            @RequestParam String eventDate,
                            @RequestParam int eventLocationId,
                            @RequestParam String description,
                            @RequestParam EventStatus status) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate;
        try {
            parsedDate = dateFormat.parse(eventDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use 'yyyy-MM-dd'");
        }

//        eventService.createEvent(eventName, parsedDate, eventLocationId, description, status);
        return eventService.listAllEvents().size();
    }



    // sample run
    // http://localhost:8080/events/create?eventName=SampleEvent&eventDate=2024-11-15&eventLocationId=1&description=Annual%20Charity%20Event&status=UPCOMING

    // Update event endpoint
    @PutMapping("/update/{id}")
    public boolean updateEvent(@PathVariable Long id,
                               @RequestParam String eventName,
                               @RequestParam String eventDate, // Change to String
                               @RequestParam int eventLocationId,
                               @RequestParam String description,
                               @RequestParam EventStatus status) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate;
        try {
            parsedDate = dateFormat.parse(eventDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use 'yyyy-MM-dd'");
        }
//        return eventService.update(id, eventName, parsedDate, eventLocationId, description, status);
        return true;
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

    @PostMapping("/register")
    public String registerEvent(){
//        eventRegistrationService.register(new EventRegistration());
        return "ListEventsView";
    }

}