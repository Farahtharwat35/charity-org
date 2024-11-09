package com.charity_org.demo.Controllers.StrategyComponents;
import com.charity_org.demo.DTO.LoginRequest;
import org.springframework.stereotype.Component;

// Facebook Login Strategy
@Component("facebook")
public class FacebookLoginStrategy implements LoginStrategyInterface {
    @Override
    public boolean login(LoginRequest loginRequest) {
        //validate the token
        String token = loginRequest.getToken();
        if (token == null || token.isEmpty()) {
            return false;
        }
        return "valid_facebook_token".equals(token);  // Mocking validation
    }
}
