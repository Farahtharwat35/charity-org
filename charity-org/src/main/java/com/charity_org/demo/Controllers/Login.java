package com.charity_org.demo.Controllers;

import com.charity_org.demo.DTO.LoginRequest;
import com.charity_org.demo.Controllers.StrategyComponents.LoginStrategyInterface;
import com.charity_org.demo.Models.Service.RolesDecorator.UserService;
import com.charity_org.demo.Models.User;
import com.charity_org.demo.Middlware.cookies.CookieHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

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
        LoginStrategyInterface loginStrategy = loginStrategies.get(provider.toLowerCase());
        User user = userService.getUserByEmail(loginRequest.getEmail());

        if (user == null) {
            model.addAttribute("error", "User not found. Please sign up first.");
            return "login";
        }

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            model.addAttribute("error", "Wrong Password, Try again!");
            return "login";
        }

        if (loginStrategy == null) {
            model.addAttribute("error", "Unsupported provider.");
            return "login";
        }

        boolean isAuthenticated = loginStrategy.login(loginRequest);

        if (isAuthenticated) {
            String sessionId = UUID.randomUUID().toString();

            cookieHandler.setCookie("SESSION_ID", sessionId, 3600, response, request, "/", user.getId());

            model.addAttribute("success", "Authenticated with " + provider + " successfully.");
            return "login_success";
        } else {
            model.addAttribute("error", "Authentication failed for " + provider + ".");
            return "login";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
        // Delete session cookie
        cookieHandler.removeCookie("SESSION_ID", request, response);

        model.addAttribute("message", "You have been logged out successfully.");
        return "login"; // Redirect to login page
    }
}
