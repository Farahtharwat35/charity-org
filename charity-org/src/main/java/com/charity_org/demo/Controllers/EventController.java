package com.charity_org.demo.Controllers;
import com.charity_org.demo.Classes.Proxy.IEventService;
import com.charity_org.demo.Models.Model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequestMapping("/events") // This adds an initial path to all endpoints in this controller
public class EventController {
    private final IEventService eventService;
    @Autowired
    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/all")
    public String getEvents(HttpServletRequest request, Model model) {
        String clientIp = request.getRemoteAddr();
        String query = request.getQueryString();
        try {


            if (query == null) {
                List<Event> events = eventService.listAllUnDeletedEvents(clientIp, query);
                model.addAttribute("events", events);
                return "ListEventsView";
            }
            String decodedFilter = URLDecoder.decode(query, StandardCharsets.UTF_8);
            List<Event> events = eventService.listAllUnDeletedEvents(clientIp, decodedFilter);
            model.addAttribute("events", events);
            return "ListEventsView";
        }catch (SecurityException e){
            model.addAttribute("errorTitle", "Security Exception");
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }


    @GetMapping("/get/{id}")
    public Event getById(@PathVariable Long id) {
        return eventService.getEvent(id);
    }

    //
//    @GetMapping("/create")
//    public long createEvent(@RequestParam String eventName,
//                            @RequestParam String eventDate,
//                            @RequestParam int eventLocationId,
//                            @RequestParam String description,
//                            @RequestParam EventStatus status) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date parsedDate;
//        try {
//            parsedDate = dateFormat.parse(eventDate);
//        } catch (ParseException e) {
//            throw new IllegalArgumentException("Invalid date format. Use 'yyyy-MM-dd'");
//        }
//
//        eventService.createEvent(eventName, parsedDate, eventLocationId, description, status);
//        return eventService.listAllUnDeletedEvents().size();
//    }
    // sample run
    // http://localhost:8080/events/create?eventName=SampleEvent&eventDate=2024-11-15&eventLocationId=1&description=Annual%20Charity%20Event&status=UPCOMING

    // Update event endpoint
//    @PutMapping("/update/{id}")
//    public boolean updateEvent(@PathVariable Long id,
//                               @RequestParam String eventName,
//                               @RequestParam String eventDate, // Change to String
//                               @RequestParam int eventLocationId,
//                               @RequestParam String description,
//                               @RequestParam EventStatus status) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date parsedDate;
//        try {
//            parsedDate = dateFormat.parse(eventDate);
//        } catch (ParseException e) {
//            throw new IllegalArgumentException("Invalid date format. Use 'yyyy-MM-dd'");
//        }
//        return eventService.update(id, eventName, parsedDate, eventLocationId, description, status);
//    }

    // Delete event endpoint
}