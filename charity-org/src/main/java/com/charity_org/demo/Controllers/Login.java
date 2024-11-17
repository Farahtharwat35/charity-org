package com.charity_org.demo.Controllers;

import com.charity_org.demo.DTO.LoginRequest;
import com.charity_org.demo.Controllers.StrategyComponents.LoginStrategyInterface;
import com.charity_org.demo.Models.Service.RolesDecorator.UserService;
import com.charity_org.demo.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class Login {
    @Autowired
    UserService userService;
    private final Map<String, LoginStrategyInterface> loginStrategies;

    @Autowired
    public Login(Map<String, LoginStrategyInterface> loginStrategies) {
        this.loginStrategies = loginStrategies;
    }

    // Serve the login page
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login"; // This corresponds to login.html
    }

    @PostMapping("/login")
    public String login(@RequestParam String provider, @ModelAttribute LoginRequest loginRequest, Model model) {
        LoginStrategyInterface loginStrategy = loginStrategies.get(provider.toLowerCase());
        User user = userService.getUserByEmail(loginRequest.getEmail());

// Check if the user exists
        if (user == null) {
            model.addAttribute("error", "User not found. Please sign up first.");
            return "login"; // Stop execution and return to login page
        }

// Check if the password is correct
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            model.addAttribute("error", "Wrong Password, Try again!");
            return "login"; // Stop execution and return to login page
        }


        if (loginStrategy == null) {
            model.addAttribute("error", "Unsupported provider.");
            return "login";
        }
        boolean isAuthenticated = loginStrategy.login(loginRequest);
        if (isAuthenticated) {
            model.addAttribute("success", "Authenticated with " + provider + " successfully.");
            return "login_success"; // Replace with a valid success page
        } else {
            model.addAttribute("error", "Authentication failed for " + provider + ".");
            return "login";
        }
    }

}
