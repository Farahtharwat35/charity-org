package com.charity_org.demo.Controllers;

import com.charity_org.demo.DTO.SignUpRequest;
import com.charity_org.demo.Models.Address;
import com.charity_org.demo.Models.User;
import com.charity_org.demo.Models.repository.AddressRepository;
import com.charity_org.demo.Models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/signup")
public class SignUp {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;// Assume we have a UserRepository to interact with the database
    private UserRepository userRepository;  // Assume we have a UserRepository to interact with the database

    @PostMapping
    public ResponseEntity<?> signup(@RequestBody @Valid SignUpRequest signupRequest, BindingResult bindingResult) {

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        // Check if user already exists (e.g., by email)
        if (userRepository.getUserByEmail(signupRequest.getEmail()) != null) {
            return ResponseEntity.status(409).body("User already exists with this email.");
        }

        // Create new User entity
        User newUser = new User();
        newUser.setName(signupRequest.getName());
        newUser.setAddress(addressRepository.findById(signupRequest.getAddressId()).orElse(null));
        newUser.setAddressId(signupRequest.getAddressId());
        newUser.setEmail(signupRequest.getEmail());
        newUser.setPassword(signupRequest.getPassword());  // In production, remember to encrypt the password
        newUser.setAge(signupRequest.getAge());

        // Save to database
        userRepository.save(newUser);

        // Return success response
        return ResponseEntity.ok("User registered successfully.");
    }
}
