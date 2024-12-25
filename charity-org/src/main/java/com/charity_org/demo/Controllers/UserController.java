package com.charity_org.demo.Controllers;
import com.charity_org.demo.DTO.UserDTO;
import com.charity_org.demo.Enums.DonationStatus;
import com.charity_org.demo.Enums.EventStatus;
import com.charity_org.demo.Middlware.cookies.CookieHandler;
import com.charity_org.demo.Middlware.cookies.Session;
import com.charity_org.demo.Middlware.cookies.SessionRepository;
import com.charity_org.demo.Models.Address;
import com.charity_org.demo.Models.Donation;
import com.charity_org.demo.Models.Event;
import com.charity_org.demo.Models.Service.DonationService;
import com.charity_org.demo.Models.Service.EventService;
import com.charity_org.demo.Models.Service.RolesDecorator.UserService;
import com.charity_org.demo.Models.User;
import com.charity_org.demo.Patcher.Patcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
@EnableWebMvc
@Configuration
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Patcher patcher;

    @Autowired
    CookieHandler cookieHandler;

    @Autowired
    SessionRepository sessionRepository;

    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id, Model model , HttpServletRequest request) {
        String sessionId = cookieHandler.getCookieValue("SESSION_ID", request);
        User user = cookieHandler.getUserFromSession(sessionId);
        if (user == null) {
            model.addAttribute("errorMessage", "User not found");
            return "error";
        }
        model.addAttribute("user", user);
        return "user-details";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        User user = userService.getUser(id);
        if (user == null) {
            model.addAttribute("errorMessage", "User not found");
            return "error";
        }
        model.addAttribute("user", user);
        return "user-form"; // View for editing user details
    }

    @PostMapping("update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user, Model model) {
        User existingUser = userService.getUser(id);
        if (existingUser == null) {
            model.addAttribute("errorMessage", "User not found");
            return "error";
        }
//        try {
//            patcher.objectPatcher(existingUser, user);
//            userService.updateUserdata(existingUser);
//        } catch (Exception e) {
//            model.addAttribute("errorMessage", "Internal server error: " + e.getMessage());
//            return "error";
//        }
        userService.updateUserdata(user);
        model.addAttribute("user", userService.getUser(id));
        return "user-details"; // Redirect to the user list or success page
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        boolean isDeleted = userService.deleteUser(id);
        if (!isDeleted) {
            model.addAttribute("errorMessage", "User not found");
            return "error";
        }
        return "redirect:/users"; // Redirect to the user list
    }

    @GetMapping("/events/{id}")
    public String getEvents(@PathVariable Long id, Model model) {
        EventService eventService = new EventService();
        User user = userService.getUser(id);
        if (user == null) {
            model.addAttribute("errorMessage", "User not found");
            return "error";
        }
        // Mock data for events
        List<Event> events = new ArrayList<>();
        Address address = new Address();
        address.setName("123 Main St");

        events.add(new Event(
                "Charity Run 2024",
                new Date(),
                address,
                "Join us for a fun and fulfilling charity run!",
                EventStatus.UPCOMING
        ));

        events.add(new Event(
                "Food Drive 2024",
                new Date(System.currentTimeMillis() + 86400000L), // Event date: tomorrow
                address,
                "Help us collect and distribute food to those in need.",
                EventStatus.ONGOING
        ));

        events.add(new Event(
                "Music Concert Fundraiser",
                new Date(System.currentTimeMillis() + 2 * 86400000L), // Event date: in 2 days
                address,
                "An evening of music and fun to support our cause.",
                EventStatus.ONGOING
        ));

        model.addAttribute("events", events);
        return "events-list";
    }

    @GetMapping("/donations/{id}")
    public String getDonations(@PathVariable Long id, Model model) {
        DonationService donationService = new DonationService();
        User user = userService.getUser(id);
        if (user == null) {
            model.addAttribute("errorMessage", "User not found");
            return "error";
        }
        // Mock data for donations

        Donation donation1 = new Donation();
        donation1.setUser(user);
        donation1.setDate(new Date()); // Current date
        donation1.setTime(new Time(System.currentTimeMillis()));
        donation1.setStatus(DonationStatus.COMPLETED);
        donation1.setDonationTotalPrice(250.00);

        // Donation 2
        Donation donation2 = new Donation();
        donation2.setUser(user);
        donation2.setDate(new Date(System.currentTimeMillis() - 86400000L)); // Yesterday
        donation2.setTime(new Time(System.currentTimeMillis() - 86400000L));
        donation2.setStatus(DonationStatus.PENDING);
        donation2.setDonationTotalPrice(500.00);
        // Donation 3
        Donation donation3 = new Donation();
        donation3.setUser(user);
        donation3.setDate(new Date(System.currentTimeMillis() - 2 * 86400000L)); // Two days ago
        donation3.setTime(new Time(System.currentTimeMillis() - 2 * 86400000L));
        donation3.setStatus(DonationStatus.CANCELLED);
        donation3.setDonationTotalPrice(100.00);
        List<Donation> donations = new ArrayList<>() {{
            add(donation1);
            add(donation2);
            add(donation3);
        }};
        model.addAttribute("donations", donations);
        return "donations-list";
    }

//    @PostMapping("/register/{eventID}")
//    public String registerForEvent(@PathVariable Long eventID, @ModelAttribute("user") User user, Model model) {
//        EventService eventService = new EventService();
//        Event event = eventService.getEvent(eventID);
//        if (event == null) {
//            model.addAttribute("errorMessage", "Event not found");
//            return "error";
//        }
//        // Register the user for the event
//        // Add the user to the event's list of participants
//        event.addParticipant(user);
//        // Update the event in the database
//        eventService.updateEvent(event);
//        model.addAttribute("user", user);
//        return "user-details"; // Redirect to the user details page
//    }

@ExceptionHandler(Exception.class)
public String handleException(Exception e, Model model) {
    model.addAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
    return "error"; // Generic error view
}
}
