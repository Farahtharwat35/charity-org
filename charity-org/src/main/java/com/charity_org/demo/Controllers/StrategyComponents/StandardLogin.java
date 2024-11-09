package com.charity_org.demo.Controllers.StrategyComponents;
import com.charity_org.demo.DTO.LoginRequest;
import com.charity_org.demo.Models.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Google Login Strategy
@Component("standard")
public class StandardLogin implements LoginStrategyInterface {
    @Autowired
    private UserService userService;

    @Override
    public boolean login(LoginRequest loginRequest) {


        String email = loginRequest.getEmail();
        if (email == null || email.isEmpty()) {
            return false;
        }

        String password = loginRequest.getPassword();
        if (password == null || password.isEmpty()) {
            return false;

        }
        UserService userService = new UserService();
        return userService.getUserByEmailAndPassword(email, password) != null;
    }
}
