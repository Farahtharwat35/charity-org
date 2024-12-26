package com.charity_org.demo.Classes.StrategyComponents;
import com.charity_org.demo.DTO.LoginRequest;
import org.springframework.stereotype.Component;

// Facebook Login Strategy
@Component("facebook")
public class FacebookLoginStrategy implements LoginStrategyInterface {
    @Override
    public boolean login(LoginRequest loginRequest) {
        //validate the token
      return true;  // Mocking validation
    }
}
