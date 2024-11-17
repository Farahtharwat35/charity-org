package com.charity_org.demo.Controllers.StrategyComponents;

import com.charity_org.demo.DTO.LoginRequest;
import org.springframework.stereotype.Component;

// Google Login Strategy
@Component("google")
public class GoogleLoginStrategy implements LoginStrategyInterface {
    @Override
    public boolean login(LoginRequest loginRequest) {
        // Simulate authentication logic for Google
        if (loginRequest.getToken() != null && !loginRequest.getToken().isEmpty()) {
            // Mock token validation (replace with actual validation)
            return true; // Assuming token is valid
        }
        return false; // If no token is provided or token is invalid
    }
}

