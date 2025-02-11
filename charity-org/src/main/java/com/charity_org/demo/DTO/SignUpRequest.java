package com.charity_org.demo.DTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.*;

@Data

public class SignUpRequest {
    public interface Create {}

    @NotEmpty(message = "Name is required.", groups = Create.class)
    @JsonProperty("name")
    private String name;

//    @NotNull(message = "Address ID is required.", groups = Create.class)
//    @JsonProperty("address_id")
//    private long addressId;




    @NotEmpty(message = "Email is required.", groups = Create.class)
    @Email(message = "Invalid email format.", groups = Create.class)
    @JsonProperty("email")
    private String email;

    @NotEmpty(message = "Address is required.", groups = Create.class)
    @JsonProperty("address")
    private String address;
    @NotEmpty(message = "Password is required.", groups = Create.class)
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters.", groups = Create.class)
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,20}",
            message = "Password must contain at least one letter, one number, and one special character.", groups = Create.class)
    @JsonProperty("password")
    private String password;


    @Min(value = 18, message = "Age must be at least 18.", groups = Create.class)
    @Max(value = 100, message = "Age must be at most 100.", groups = Create.class)
    @JsonProperty("age")
    private int age;
}