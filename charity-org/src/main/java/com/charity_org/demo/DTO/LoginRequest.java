package com.charity_org.demo.DTO;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
    private String token;

}
