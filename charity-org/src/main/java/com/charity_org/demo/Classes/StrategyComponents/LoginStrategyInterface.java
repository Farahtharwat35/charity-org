package com.charity_org.demo.Classes.StrategyComponents;

import com.charity_org.demo.DTO.LoginRequest;

import java.util.Map;

public interface LoginStrategyInterface{
    public Map<String, Object> login(LoginRequest loginRequest);
}
