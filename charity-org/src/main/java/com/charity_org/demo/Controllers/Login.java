package com.charity_org.demo.Controllers;

import com.charity_org.demo.DTO.LoginRequest;
import com.charity_org.demo.Classes.StrategyComponents.LoginStrategyInterface;
import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Models.Service.UserService;
import com.charity_org.demo.Middlware.cookies.CookieHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping("/auth")
public class Login {
    @Autowired
    private UserService userService;

    private final Map<String, LoginStrategyInterface> loginStrategies;
    private final CookieHandler cookieHandler;

    @Autowired
    public Login(Map<String, LoginStrategyInterface> loginStrategies, CookieHandler cookieHandler) {
        this.loginStrategies = loginStrategies;
        this.cookieHandler = cookieHandler;
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String provider,
            @ModelAttribute LoginRequest loginRequest,
            Model model,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        Logger logger = LoggerFactory.getLogger(this.getClass());

        logger.info("Login attempt started with provider: {}", provider);

        // Fetch login strategy
        LoginStrategyInterface loginStrategy = loginStrategies.get(provider.toLowerCase());

        if (loginStrategy == null) {
            logger.info("No login strategy found for provider: {}", provider);
            model.addAttribute("error", "Unsupported provider.");
            return "login";
        }

        logger.info("Login strategy found for provider: {}", provider);

        Map<String, Object> result = loginStrategy.login(loginRequest);
        String isAuthenticated = (String) result.get("error");

        if (isAuthenticated != null) {
            model.addAttribute("error", isAuthenticated);
            return "login";
        }

        User user = (User) result.get("user");

        logger.info("User found with email: {}", loginRequest.getEmail());


        logger.info("Password verification successful for user: {}", loginRequest.getEmail());

        String sessionId = UUID.randomUUID().toString();
        logger.info("Authentication successful. Generated session ID: {}", sessionId);

        // Set the session cookie
        cookieHandler.setCookie("SESSION_ID", sessionId, 3600, response, request, "/", user.getId());
        logger.info("Session cookie set successfully for user: {}", loginRequest.getEmail());

        model.addAttribute("success", "Authenticated with " + provider + " successfully.");
        return "HomePage";

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
        // Delete session cookie
        cookieHandler.removeCookie("SESSION_ID", request, response);

        model.addAttribute("message", "You have been logged out successfully.");
        return "login"; // Redirect to login page
    }
}
