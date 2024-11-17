package com.charity_org.demo.Controllers;

import com.charity_org.demo.DTO.PostOrPutEventRequest;

import com.charity_org.demo.Models.Event;
import com.charity_org.demo.Models.Service.AddressService;
import com.charity_org.demo.Models.Service.EventService;
import com.charity_org.demo.Patcher.Patcher;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;



@Service
@Controller
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private Patcher patcher;
    @Autowired
    private EventService eventService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ModelMapper modelMapper;

    public AdminController(EventService eventService,ModelMapper modelMapper) {
        this.eventService = eventService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add_event")
    //return add event form
    public String addEventForm(Model model) {
        model.addAttribute("event", new PostOrPutEventRequest());
        return "create-event-form";
    }

    @PostMapping("/save_event")
    public String saveEvent(@ModelAttribute("event") @Validated({PostOrPutEventRequest.Create.class, Default.class}) PostOrPutEventRequest event, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // add error msg
            model.addAttribute("error", "Invalid input. Please check your input.");
            // return the form with error msg
            addEventForm(model);
        }
        eventService.createEvent(event.getEventName(), event.getEventDate(), event.getEventLocationId(), event.getDescription());
        return "redirect:/api/admin/events";
    }

    @GetMapping("/events")
    public String listEvents(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "events";
    }
    @PostMapping("/delete_event/{Id}")
    public String deleteEvent(@PathVariable Long Id) {
        eventService.deleteEvent(Id);
        return "redirect:/api/admin/events";
    }
    @GetMapping("/edit_event/{Id}")
    public String editEvent(@PathVariable Long Id, Model model) {
        model.addAttribute("event", eventService.getEvent(Id));
        return "edit-event-form";
    }
    @PostMapping("/update_event/{Id}")
    public String updateEvent(@PathVariable Long Id, @ModelAttribute("event") Event event, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors());
            return "edit-event-form";
        }
        eventService.updateEvent(Id, event);
        return "redirect:/api/admin/events";
    }

}
