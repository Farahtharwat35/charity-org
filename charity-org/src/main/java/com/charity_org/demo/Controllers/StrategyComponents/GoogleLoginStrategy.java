package com.charity_org.demo.Controllers.StrategyComponents;

import com.charity_org.demo.DTO.LoginRequest;
import org.springframework.stereotype.Component;

// Google Login Strategy
@Component("google")
public class GoogleLoginStrategy implements LoginStrategyInterface {
    @Override
    public boolean login(LoginRequest loginRequest) {
        String token = loginRequest.getToken();
        if (token == null || token.isEmpty()) {
            return false;
        }
        return "valid_google_token".equals(token);  // Mocking validation
    }
}
