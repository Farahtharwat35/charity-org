package com.charity_org.demo.DTO;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

@Data
public class SignUpRequest {

    @NotEmpty(message = "Name is required.")
    private String name;

    @NotEmpty(message = "Address ID is required.")
    private long addressId;

    @NotEmpty(message = "Email is required.")
    private String email;

    @NotEmpty(message = "Password is required.")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters.")
    private String password;

    private int age;

}