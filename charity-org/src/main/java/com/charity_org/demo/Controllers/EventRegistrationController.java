package com.charity_org.demo.Controllers;
import com.charity_org.demo.Classes.CommandComponents.EventCommand;
import com.charity_org.demo.Classes.CommandComponents.Invoker;
import com.charity_org.demo.Classes.CommandComponents.SubscribeToEventCommand;
import com.charity_org.demo.Classes.CommandComponents.UnsubscribeToEventCommand;
import com.charity_org.demo.Classes.ObserverComponents.IEventObserver;
import com.charity_org.demo.Classes.ObserverComponents.IEventSubject;
import com.charity_org.demo.Middlware.cookies.CookieHandler;
import com.charity_org.demo.Models.Model.Event;
import com.charity_org.demo.Models.Model.EventRegistration;
import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Models.Service.EmailNotificationService;
import com.charity_org.demo.Models.Service.EventRegistrationService;
import com.charity_org.demo.Models.Service.EventService;
import com.charity_org.demo.Models.Service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/event-registration") // This adds an initial path to all endpoints in this controller
public class EventRegistrationController {
    @Autowired
    private EventService eventService;
    @Autowired
    CookieHandler cookieHandler;
    @Autowired
    UserRoleService userRoleService;


    //private EventRegistration eventRegistration = new EventRegistration();

    @Autowired
    private EventRegistrationService eventRegistrationService;

    @GetMapping("/getAllEvents")
    public String getAllEvents(HttpServletRequest request, Model model) {
        String clientIp = request.getRemoteAddr();
        String query = request.getQueryString();
        List<Event> events = eventService.listAllUnDeletedEvents(clientIp, query); User user= cookieHandler.getUserFromSession(request);
        String name =userRoleService.getRole(request);
        model.addAttribute("role", name);
        model.addAttribute("userID", user.getId());
        model.addAttribute("events", events);
        return "ListEventsView";
    }

    @PostMapping("/registerEvent")
    public String eventRegistration(@RequestParam("event.id") Long eventId, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        // Fetch the Event object by ID
        Event event = eventService.getById(eventId);

        // Check if the event exists
        if (event == null) {
            redirectAttributes.addFlashAttribute("error", "Event not found!");
            return "redirect:/event-registration/getAllEvents";
        }

        // Retrieve the user from the session
        User user = cookieHandler.getUserFromSession(request);

        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "User not logged in!");
            return "redirect:/login"; // Redirect to login if user is not found in session
        }

        // Check if the user is already registered for the event
        EventRegistration isAlreadyRegistered = eventRegistrationService.isUserRegisteredForEvent(user.getId(), eventId);
        if (isAlreadyRegistered!=null) {
            Long idd=isAlreadyRegistered.getId();
            redirectAttributes.addFlashAttribute("error", "You are already registered for this event!"+idd);
            return "redirect:/event-registration/getAllEvents";
        }

        Date currentDateTime = new Date();

        // Create the event registration object
        EventRegistration eventRegistration = new EventRegistration();
        eventRegistration.setEvent(event);
        eventRegistration.setRegisteredAt(currentDateTime);
        eventRegistration.setUser(user);

        // Register the user for the event
        //eventRegistrationService.register(eventRegistration);

        // Setup event notification using the observer pattern
        EventService eventSubject = new EventService();
        IEventObserver eventObserver = new EmailNotificationService(eventSubject, "mariamwahdan32@gmail.com");

        String subject = "Event Registration Confirmation";
        String content = "You have successfully registered for the event: " + event.getEventName();

        EventCommand subscribeToEventCommand = new SubscribeToEventCommand(
                eventSubject,
                eventObserver,
                subject,
                content,
                eventRegistrationService,
                eventRegistration
        );

        Invoker commandInvoker = new Invoker(subscribeToEventCommand);
        commandInvoker.executeCommand();

        // Add success message to the redirect attributes
        redirectAttributes.addFlashAttribute("message", "Event registered successfully!");

        return "redirect:/event-registration/getAllEvents";
    }


    @PostMapping("/unregisterEvent")
    public String eventUnregistration(@RequestParam("eventId") Long eventId, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        // Fetch the Event object by ID
        Event event = eventService.getById(eventId);

        // Retrieve the user from the session
        User user = cookieHandler.getUserFromSession(request);

        // Check if the event exists
        if (event == null) {
            redirectAttributes.addFlashAttribute("error", "Event not found!");
            return "redirect:/user/events/" + user.getId();
        }


        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "User not logged in!");
            return "redirect:/login"; // Redirect to login if user is not found in session
        }

        // Check if the user is registered for the event
        EventRegistration eventRegistration = eventRegistrationService.isUserRegisteredForEvent(user.getId(), eventId);
        if (eventRegistration==null) {
            redirectAttributes.addFlashAttribute("error", "You are not registered for this event!");
            return "redirect:/user/events/" + user.getId();
        }
        // Setup event notification using the observer pattern
        EventService eventSubject = new EventService();
        IEventObserver eventObserver = new EmailNotificationService(eventSubject, "mariamwahdan32@gmail.com");
        EventCommand unsubscribeFromEventCommand = new UnsubscribeToEventCommand(
                eventSubject,
                eventObserver,
                eventRegistrationService,
                eventRegistration
        );

        Invoker commandInvoker = new Invoker(unsubscribeFromEventCommand);
        commandInvoker.executeCommand();

        // Add success message to the redirect attributes
        redirectAttributes.addFlashAttribute("message", "Event unregistered successfully!");

        return "redirect:/user/events/" + user.getId();
    }


}
