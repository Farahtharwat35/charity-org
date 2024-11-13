package com.charity_org.demo.Controllers;


import com.charity_org.demo.DTO.SignUpRequest;
import com.charity_org.demo.Models.User;
import com.charity_org.demo.Models.repository.AddressRepository;
import com.charity_org.demo.Models.Service.RolesDecorator.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/signup")
public class SignUp {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressRepository addressRepository;// Assume we have a UserRepository to interact with the database

    @PostMapping
    public ResponseEntity<?> signup(@RequestBody @Valid SignUpRequest signupRequest, BindingResult bindingResult) {

        // Checking for validation errors
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        // Checking if user already exists (e.g., by email)
        if (userService.getUserByEmail(signupRequest.getEmail()) != null) {
            return ResponseEntity.status(409).body("User already exists with this email.");
        }

        // Save to database
        userService.save(
                signupRequest.getName(),
                signupRequest.getEmail(),
                signupRequest.getPassword(),
                signupRequest.getAddressId(),
                signupRequest.getAge()
        );

        // Return success response
        return ResponseEntity.ok("User registered successfully.");
    }
}
