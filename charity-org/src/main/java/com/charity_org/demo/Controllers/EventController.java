package com.charity_org.demo.Controllers;
import com.charity_org.demo.Classes.IteratorComponents.EventIterator;
import com.charity_org.demo.Classes.Proxy.IEventService;
import com.charity_org.demo.Models.Model.Address;
import com.charity_org.demo.Models.Model.Event;

import com.charity_org.demo.Models.Service.UserRoleService;

import com.charity_org.demo.Models.Service.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import jakarta.servlet.http.HttpServletRequest;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequestMapping("/events") // This adds an initial path to all endpoints in this controller
public class EventController {
    @Autowired
    UserRoleService userRoleService;
    private final IEventService eventService;

    @Autowired
    AddressService addressService;

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
                String name =userRoleService.getRole(request);
                model.addAttribute("role", name);
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

    // Delete event endpoint
    @DeleteMapping("/delete/{id}")
    public boolean deleteEvent(@RequestHeader("X-Forwarded-For") String clientIp, @PathVariable Long id) {
        return eventService.deleteById(clientIp, id);
    }

    @GetMapping("/search")
    public String searchEvents(@RequestParam("keyword") String keyword, Model model,HttpServletRequest request) {
        List<Event> events;
        String clientIp = request.getRemoteAddr();
        String query = request.getQueryString();
        if (keyword == null || keyword.isEmpty()) {
            if (query == null) {
                events = eventService.listAllUnDeletedEvents(clientIp, query);
            }
            else {
                String decodedFilter = URLDecoder.decode(query, StandardCharsets.UTF_8);
                events = eventService.listAllUnDeletedEvents(clientIp, decodedFilter);
            }
        } else {
            EventIterator iterator = eventService.createSearchIterator(clientIp, query, keyword);
            events = new ArrayList<>();
            while (iterator.hasNext()) {
                events.add(iterator.next());
            }
        }
        model.addAttribute("events", events);
        model.addAttribute("keyword", keyword);
        return "ListEventsView"; // View for displaying search results
    }

    @GetMapping("/cities")
    @ResponseBody
    public List<Address> getCitiesByCountry(@RequestParam("countryId") Long countryId) {
        return addressService.getCitiesByParentId(countryId);
    }


}