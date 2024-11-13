package com.charity_org.demo.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private long id;
    private String email;
    private String password;
    private String name;
}
