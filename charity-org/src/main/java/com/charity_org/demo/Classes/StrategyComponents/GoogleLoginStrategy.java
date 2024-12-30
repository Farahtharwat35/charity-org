package com.charity_org.demo.Classes.StrategyComponents;

import com.charity_org.demo.DTO.LoginRequest;
import org.springframework.stereotype.Component;

import java.util.Map;

// Google Login Strategy
@Component("google")
public class GoogleLoginStrategy implements LoginStrategyInterface {
    @Override
    public Map<String, Object> login(LoginRequest loginRequest) {

            return null; // Assuming token is valid
       // If no token is provided or token is invalid
    }
}

