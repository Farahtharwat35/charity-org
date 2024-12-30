package com.charity_org.demo.Classes.StrategyComponents;
import com.charity_org.demo.DTO.LoginRequest;
import org.springframework.stereotype.Component;

import java.util.Map;

// Facebook Login Strategy
@Component("facebook")
public class FacebookLoginStrategy implements LoginStrategyInterface {
    @Override
    public Map<String, Object> login(LoginRequest loginRequest) {
        //validate the token
      return null;  // Mocking validation
    }
}
