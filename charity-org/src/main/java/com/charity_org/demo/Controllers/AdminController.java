package com.charity_org.demo.Controllers;

import com.charity_org.demo.DTO.PostOrPutEventRequest;
import com.charity_org.demo.Models.Event;
import com.charity_org.demo.Models.Service.EventService;
import com.charity_org.demo.Patcher.Patcher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;

@Service
@RestController("/api/admin")
public class AdminController {
    @Autowired
    private Patcher patcher;
    @Autowired
    private EventService eventService;

    @Autowired
    private ModelMapper modelMapper;

    public AdminController(EventService eventService,ModelMapper modelMapper) {
        this.eventService = eventService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create_event")
    public ResponseEntity<?> createEvent(@RequestBody @Validated({PostOrPutEventRequest.Create.class, Default.class}) PostOrPutEventRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        boolean createdEvent = eventService.createEvent(request.getEventName(), request.getEventDate(), request.getEventLocationId(), request.getDescription());
        return ResponseEntity.ok(createdEvent);
    }
    @PutMapping("/update_event/{Id}")
    public ResponseEntity<Object> updateEvent(@RequestBody @Validated PostOrPutEventRequest request, BindingResult bindingResult, @PathVariable long Id) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        Event event = eventService.getEvent(Id);
        Event updatedEvent = modelMapper.map(request, Event.class);

        try {
            patcher.objectPatcher(event, updatedEvent);
            eventService.updateEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().body(event);
    }

    @DeleteMapping("/delete_event/{Id}")
    public ResponseEntity<Object> deleteEvent(@PathVariable long Id) {
        if (!eventService.deleteEvent(Id)) {
            return ResponseEntity.badRequest().body("Event not found");
        }
        return ResponseEntity.ok().body("Event deleted successfully");
    }
}
