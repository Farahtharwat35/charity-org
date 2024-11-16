package com.charity_org.demo.DTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SignUpRequest {
    public interface Create {}

    @NotEmpty(message = "Name is required.", groups = Create.class)
    @JsonProperty("name")
    private String name;

    @NotNull(message = "Address ID is required.", groups = Create.class)
    @JsonProperty("address_id")
    private long addressId;

    @NotEmpty(message = "Email is required.", groups = Create.class)
    @JsonProperty("email")
    private String email;

    @NotEmpty(message = "Password is required.", groups = Create.class)
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters.", groups = Create.class)
    @JsonProperty("password")
    private String password;

    @JsonProperty("age")
    private int age;

}