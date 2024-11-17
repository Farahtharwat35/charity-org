package com.charity_org.demo.DTO;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;


public class UserDTO {

    public interface Create {}

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

    public void setName(@NotNull(message = "Name cannot be blank", groups = Create.class) String name) {
        this.name = name;
    }

    public void setAge(@NotNull(message = "Age is required", groups = Create.class) Integer age) {
        this.age = age;
    }

    public void setPassword(@NotBlank(message = "Password is required", groups = Create.class) String password) {
        this.password = password;
    }

    public void setEmail(@NotBlank(message = "Email is required", groups = Create.class) String email) {
        this.email = email;
    }

    public Long getAddressId() {
        return addressId;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
