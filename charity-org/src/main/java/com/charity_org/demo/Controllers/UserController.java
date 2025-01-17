package com.charity_org.demo.Controllers;
import com.charity_org.demo.Classes.Singleton.SingletonLogger;
import com.charity_org.demo.Enums.DonationStatus;
import com.charity_org.demo.Middlware.cookies.CookieHandler;
import com.charity_org.demo.Middlware.cookies.SessionRepository;
import com.charity_org.demo.Models.Model.Donation;
import com.charity_org.demo.Models.Model.Event;
import com.charity_org.demo.Models.Service.*;
import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Patcher.Patcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    CookieHandler cookieHandler;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    private Patcher patcher;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    private EventRegistrationService eventRegistrationService;
    @Autowired
    private DonationService donationService;

    private SingletonLogger logger=SingletonLogger.getInstance(SingletonLogger.FileFormat.PLAIN_TEXT);

    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id, Model model, HttpServletRequest request) {
        logger.log(SingletonLogger.LogLevel.INFO, "Fetching user from session...");
        User user = cookieHandler.getUserFromSession(request);
        if (user == null) {
            model.addAttribute("errorMessage", "User not found");
            return "error";
        }

        logger.log(SingletonLogger.LogLevel.INFO, "Checking if user ID matches the requested ID...");
        if (user.getId() != id) {
            logger.log(SingletonLogger.LogLevel.ERROR, "Unauthorized access: User ID {} does not match requested ID {}", user.getId(), id);
            model.addAttribute("errorMessage", "Unauthorized access");
            return "error";
        }
        logger.log(SingletonLogger.LogLevel.INFO, "User ID matches. Adding user to model...");
        model.addAttribute("user", user);
        logger.log(SingletonLogger.LogLevel.INFO, "Returning user-details view.");
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
    public String getMyEvents(@PathVariable Long id, Model model, HttpServletRequest request) {
        List<Event> events = eventRegistrationService.getEventRegistredByUser(id);
        User user= cookieHandler.getUserFromSession(request);
        String name =userRoleService.getRole(request);
        model.addAttribute("role", name);
        model.addAttribute("userID", user.getId());
        model.addAttribute("events", events);
        return "my-events-list";
    }

    @GetMapping("/donations/{id}")
    public String getDonations(@PathVariable Long id, Model model) {
        User user = userService.getUser(id);
        if (user == null) {
            model.addAttribute("errorMessage", "User not found");
            return "error";
        }
        List<Donation> donations = donationService.getDonationsByUserId(id);
        model.addAttribute("donations", donations);
        return "donations-list";
    }

    @PostMapping("/donations/cancel/{id}")
    public String cancelDonation(@PathVariable Long id, Model model, HttpServletRequest request) {
        Donation donation = donationService.getDonation(id);
        donation.cancelDonation();
        donationService.updateDonationStatus(id,donation.getDonationStatusClassName());
        User user= cookieHandler.getUserFromSession(request);
        return "redirect:/user/donations/" + user.getId();
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
