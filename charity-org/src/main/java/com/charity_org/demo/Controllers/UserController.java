package com.charity_org.demo.Controllers;
import com.charity_org.demo.DTO.UserDTO;
import com.charity_org.demo.Models.Service.UserService;
import com.charity_org.demo.Models.User;
import com.charity_org.demo.Views.UserDisplays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public UserDisplays getUser(UserDTO userDTO) {
        User user = userService.getUser(userDTO.getId());
        return null; //to be changed
    }

    @GetMapping("/users/email/{email}")
    public UserDisplays getUserByEmail(UserDTO userDTO){
        User user = userService.getUserByEmail(userDTO.getEmail());
        return null; //to be changed
    }

    @GetMapping("/users/email/{email}/password/{password}")
    public UserDisplays getUserByEmailAndPassword(@PathVariable String email, @PathVariable String password){
        User user = userService.getUserByEmailAndPassword(email, password);
        return null; // To be changed
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(UserDTO userDTO) {
        boolean isDeleted = userService.deleteUser(userDTO.getId());
        if (isDeleted) {
            return ResponseEntity.ok("User deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("User not found.");
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO) {
        User user = userService.getUser(userDTO.getId());
        if (user == null) {
            return ResponseEntity.status(404).body("User not found.");
        }
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        userService.save(user);
        return ResponseEntity.ok("User updated successfully.");
    }

    @PutMapping("/users/{id}/events/{eventId}")
    public ResponseEntity<?> registerForEvent(@PathVariable long id, @PathVariable long eventId) {
        User user = userService.getUser(id);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found.");
        }
        // Assume we have an EventService to interact with the database
        // Assume we have a method in EventService to register a user for an event
        // eventService.registerUserForEvent(user, eventId);
        return ResponseEntity.ok("User registered for event successfully.");
    }



}
