package com.charity_org.demo.Controllers;


import com.charity_org.demo.Enums.EventStatus;
import com.charity_org.demo.Models.Event;
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
@RequestMapping("/event-registration") // This adds an initial path to all endpoints in this controller
public class EventRegistrationController {
    @Autowired
    private EventService eventService;

    @Autowired
    private EventRegistrationService eventRegistrationService;

    @GetMapping("/getAllEvents")
    public String getAllEvents(Model model) {
        List<Event> events = eventService.listAllEvents();
        model.addAttribute("events", events);
        return "ListEventsView";
    }

    @PostMapping("/registerEvent")
    public String eventRegistration(@RequestParam("event.id") Long eventId, Model model) {
        // Fetch the Event object by ID
        Event event = eventService.getById(eventId);

        // If the event exists, process the registration
        if (event != null) {
            Date currentDateTime = new Date();
            boolean result = eventRegistrationService.register(event, currentDateTime);

            // You could add a confirmation message here
            model.addAttribute("confirmationMessage", "You have successfully registered for " + event.getEventName());
        } else {
            // Handle the case when the event is not found
            model.addAttribute("errorMessage", "Event not found.");
        }

        return "EventView";  // Optionally redirect to a success page or display a confirmation
    }


}
