package com.charity_org.demo.Controllers;

import com.charity_org.demo.Classes.Proxy.IEventService;
import com.charity_org.demo.DTO.PostOrPutEventRequest;
import com.charity_org.demo.Middlware.cookies.CookieHandler;
import com.charity_org.demo.Models.Model.Address;
import com.charity_org.demo.Models.Model.Event;
import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Models.Repository.AddressRepository;
import com.charity_org.demo.Models.Service.AddressService;
import com.charity_org.demo.Models.Service.UserRoleService;
import com.charity_org.demo.Patcher.Patcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    CookieHandler cookieHandler;
    private final IEventService eventService;
    private final AddressService addressService;
    private final Patcher patcher;

    @Autowired
    public AdminController(IEventService eventService, AddressService addressService, Patcher patcher) {
        this.eventService = eventService;
        this.addressService = addressService;
        this.patcher = patcher;
    }
    @Autowired
    UserRoleService userRoleService;
    @GetMapping("/add_event")
    public String addEventForm(Model model) {
        List<Address> addressesWithoutParent = addressService.findAll().stream()
                .filter(address -> address.getParent() == null)
                .collect(Collectors.toList());
        model.addAttribute("countries", addressesWithoutParent);
        model.addAttribute("event", new PostOrPutEventRequest());
        return "create-event-form";
    }

    @PostMapping("/save_event")
    public String saveEvent(HttpServletRequest request,
                            @ModelAttribute("event") @Validated({PostOrPutEventRequest.Create.class, Default.class}) PostOrPutEventRequest event,
                            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Invalid input. Please check your input.");
            return "create-event-form";
        }
        try {
            eventService.createEvent(request.getRemoteAddr(), event.getEventName(), event.getEventDate(), event.getEventLocationId(), event.getDescription());
            return "redirect:/api/admin/events";
        }catch (SecurityException e) {
            model.addAttribute("errorTitle", "Security Exception");
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/events")
    public String listEvents(HttpServletRequest request, Model model) {
        String query = request.getQueryString();
        String clientIp = request.getRemoteAddr();
        try {
            if (query == null) {
                model.addAttribute("events", eventService.getAllEvents(clientIp, query));
                User user= cookieHandler.getUserFromSession(request);
                String name =userRoleService.getRole(request);
                model.addAttribute("role", name);
                model.addAttribute("userID", user.getId());

                return "events";
            }
            String decodedFilter = URLDecoder.decode(query, StandardCharsets.UTF_8);

            model.addAttribute("events", eventService.getAllEvents(clientIp, decodedFilter));

            return "events";
        }
        catch (SecurityException e) {
            model.addAttribute("errorTitle", "Security Exception");
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/delete_event/{Id}")
    public String deleteEvent(HttpServletRequest request, @PathVariable Long Id) {
        eventService.deleteEvent(request.getRemoteAddr(), Id);
        return "redirect:/api/admin/events";
    }

    @GetMapping("/edit_event/{Id}")
    public String editEvent(@PathVariable Long Id, Model model) {

        model.addAttribute("event", eventService.getEvent(Id));
        List<Address> addressesWithoutParent = addressService.findAll().stream()
                .filter(address -> address.getParent() == null)
                .collect(Collectors.toList());
        model.addAttribute("countries", addressesWithoutParent);
        return "edit-event-form";
    }

    @PostMapping("/update_event/{Id}")
    public String updateEvent(HttpServletRequest request,
                              @PathVariable Long Id, @ModelAttribute("event") Event event,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors());
            List<Address> addressesWithoutParent = addressService.findAll().stream()
                    .filter(address -> address.getParent() == null)
                    .collect(Collectors.toList());
            model.addAttribute("countries", addressesWithoutParent);
            return "edit-event-form";
        }
        try {
            String clientIp = request.getRemoteAddr();
            eventService.updateEvent(clientIp, Id, event);
            return "redirect:/api/admin/events";
        }catch (SecurityException e) {
            model.addAttribute("errorTitle", "Security Exception");
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
}
