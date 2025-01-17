package com.charity_org.demo.Controllers;
import com.charity_org.demo.Classes.Singleton.SingletonLogger;
import com.charity_org.demo.DTO.LoginRequest;
import com.charity_org.demo.Classes.StrategyComponents.LoginStrategyInterface;
import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Models.Service.AddressService;
import com.charity_org.demo.Models.Service.UserService;
import com.charity_org.demo.Middlware.cookies.CookieHandler;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private SingletonLogger logger = SingletonLogger.getInstance(SingletonLogger.FileFormat.PLAIN_TEXT);

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
        logger.log(SingletonLogger.LogLevel.INFO, "Login attempt started with provider: {}", provider);

        LoginStrategyInterface loginStrategy = loginStrategies.get(provider.toLowerCase());

        if (loginStrategy == null) {
            logger.log(SingletonLogger.LogLevel.INFO, "No login strategy found for provider: {}", provider);
            model.addAttribute("error", "Unsupported provider.");
            return "login";
        }

        logger.log(SingletonLogger.LogLevel.INFO, "Attempting to login using provider: {}", provider);

        Map<String, Object> result = loginStrategy.login(loginRequest);
        String isAuthenticated = (String) result.get("error");

        if (isAuthenticated != null) {
            model.addAttribute("error", isAuthenticated);
            return "Login";
        }

        User user = (User) result.get("user");

        logger.log(SingletonLogger.LogLevel.INFO, "User found with email: {}", loginRequest.getEmail());

        logger.log(SingletonLogger.LogLevel.INFO, "Password verification successful for user: {}", loginRequest.getEmail());

        // Create session ID
        String sessionId = UUID.randomUUID().toString();
        logger.log(SingletonLogger.LogLevel.INFO, "Authentication successful. Generated session ID: {}", sessionId);

        // Set session cookie
        cookieHandler.setCookie("SESSION_ID", sessionId, 3600, response, request, "/", user.getId());
        logger.log(SingletonLogger.LogLevel.INFO, "Session cookie set successfully for user: {}", loginRequest.getEmail());

        userService.loadUserByUsername(loginRequest.getEmail());
        // Authenticate user and set SecurityContext
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, userService.getAuthorities(user.getRoles()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        model.addAttribute("success", "Authenticated with " + provider + " successfully.");

        return "redirect:/home/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
        // Delete session cookie
        cookieHandler.removeCookie("SESSION_ID", request, response);

        // Optionally invalidate the session if needed
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();  // Invalidate session to clear session attributes
        }

        // Add a message to the model (optional, can be displayed on the login page)
        model.addAttribute("message", "You have been logged out successfully.");

        // Redirect to the login page after successful logout
        return "redirect:/auth/login"; // Ensure the user is redirected to the login page
    }

}
