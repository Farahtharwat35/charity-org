package com.charity_org.demo.DTO;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class UserDTO {

    public interface Create {}

    @NotNull(message = "Id is required", groups = Create.class)
    private Long id;

    @NotBlank(message = "Email is required", groups = Create.class)
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "Password is required", groups = Create.class)
    @JsonProperty("password")
    private String password;

    @NotBlank(message = "Name cannot be blank", groups = Create.class)
    @JsonProperty("name")
    private String name;

    @NotNull(message = "Age is required", groups = Create.class)
    @JsonProperty("age")
    private Integer age;

    @NotNull(message = "AddressId is required", groups = Create.class)
    @JsonProperty("addressId")
    private Long addressId;
}
