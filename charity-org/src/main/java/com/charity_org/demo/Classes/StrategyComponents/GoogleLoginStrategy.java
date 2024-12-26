package com.charity_org.demo.Classes.StrategyComponents;

import com.charity_org.demo.DTO.LoginRequest;
import org.springframework.stereotype.Component;

// Google Login Strategy
@Component("google")
public class GoogleLoginStrategy implements LoginStrategyInterface {
    @Override
    public boolean login(LoginRequest loginRequest) {

            return true; // Assuming token is valid
       // If no token is provided or token is invalid
    }
}

