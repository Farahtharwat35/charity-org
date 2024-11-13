package com.charity_org.demo.Controllers;

import com.charity_org.demo.Controllers.StrategyComponents.LoginStrategyInterface;
import com.charity_org.demo.DTO.LoginRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class Login {

    private final Map<String, LoginStrategyInterface> loginStrategies;

    public Login(Map<String, LoginStrategyInterface> loginStrategies) {
        this.loginStrategies = loginStrategies;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestParam String provider, LoginRequest loginRequest) {
        LoginStrategyInterface loginStrategy = loginStrategies.get(provider.toLowerCase());

        if (loginStrategy == null) {
            return ResponseEntity.badRequest().body("Unsupported provider.");
        }

        boolean isAuthenticated = loginStrategy.login(loginRequest);
        if (isAuthenticated) {
            return ResponseEntity.ok("Authenticated with " + provider + " successfully.");
        } else {
            return ResponseEntity.status(401).body("Authentication failed for " + provider + ".");
        }
    }
}
