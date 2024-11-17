package com.charity_org.demo.Controllers;

import com.charity_org.demo.DTO.LoginRequest;
import com.charity_org.demo.Controllers.StrategyComponents.LoginStrategyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class Login {

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

    // Process the login request
    @PostMapping("/login")
    public String login(@RequestParam String provider, @ModelAttribute LoginRequest loginRequest, Model model) {
        LoginStrategyInterface loginStrategy = loginStrategies.get(provider.toLowerCase());

        if (loginStrategy == null) {
            model.addAttribute("error", "Unsupported provider.");
            return "login"; // Return to login page if the provider is unsupported
        }

        boolean isAuthenticated = loginStrategy.login(loginRequest);
        if (isAuthenticated) {
            model.addAttribute("success", "Authenticated with " + provider + " successfully.");
            return "login_success"; // Return to success page
        } else {
            model.addAttribute("error", "Authentication failed for " + provider + ".");
            return "login"; // Return to login page if authentication fails
        }
    }
}
