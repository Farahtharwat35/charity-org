package com.charity_org.demo.Classes.StrategyComponents;

import com.charity_org.demo.DTO.LoginRequest;
import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Models.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component("standard")
public class StandardLogin implements LoginStrategyInterface {

    @Autowired
    private UserService userService;

    @Override
    public Map<String, Object> login(LoginRequest loginRequest) {
        Map<String, Object> response = new HashMap<>();

        Optional<User> optionalUser = Optional.ofNullable(userService.getUserByEmail(loginRequest.getEmail()));
        if (optionalUser.isEmpty()) {
            response.put("error", "User not found");
            return response;
        }

        User user = optionalUser.get();
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            response.put("error", "Invalid password");
            return response;
        }

        response.put("user", user);
        return response;
    }
}
