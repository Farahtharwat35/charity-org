package com.charity_org.demo.DTO;

import lombok.Data;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class UserDTO {
    @NotNull(message = "Id is required")
    private Long id;  // Use Long instead of primitive long

    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "Password is required")
    private String password;

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Age is required")
    private Integer age;  // Use Integer instead of primitive int

    @NotNull(message = "AddressId is required")
    private Long addressId;  // Use Long instead of primitive long
}
