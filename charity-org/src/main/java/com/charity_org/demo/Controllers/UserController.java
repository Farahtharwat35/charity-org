package com.charity_org.demo.Controllers;
import com.charity_org.demo.DTO.ErrorResponseDTO;
import com.charity_org.demo.DTO.UserDTO;
import com.charity_org.demo.Models.Service.RolesDecorator.UserService;
import com.charity_org.demo.Models.User;
import com.charity_org.demo.Patcher.Patcher;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;



@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    Patcher patcher;

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
        //return new UserDisplays(user); // Convert User to UserDisplays DTO
    }

    @GetMapping("/users/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(user);
//        return new UserDisplays(user);
    }

    @GetMapping("/users/email/{email}/password/{password}")
    public ResponseEntity<User> getUserByEmailAndPassword(@PathVariable String email, @PathVariable String password) {
        User user = userService.getUserByEmailAndPassword(email, password);
        return ResponseEntity.status(HttpStatus.OK).body(user);
//        return new UserDisplays(user);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userData, @PathVariable Long id) {
        User existingUser = userService.getUser(id);
        if (existingUser == null) {
            // Return an ErrorResponseDTO with a status code and message when user is not found
            ErrorResponseDTO errorResponse = new ErrorResponseDTO("User not found", HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        try {
            patcher.objectPatcher(existingUser, userData);
            userService.updateUserdata(existingUser);
        } catch (Exception e) {
            e.printStackTrace();
            // Return an ErrorResponseDTO with a status code and message on exception
            ErrorResponseDTO errorResponse = new ErrorResponseDTO("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
        return ResponseEntity.status(HttpStatus.OK).body(existingUser);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Collect validation errors
            StringBuilder errorMessage = new StringBuilder("Validation failed: ");
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorMessage.append(error.getDefaultMessage()).append(" ");
            }

            // Create an ErrorResponseDTO with the error message and status code
            ErrorResponseDTO errorResponse = new ErrorResponseDTO(errorMessage.toString().trim(), HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        // If no validation errors, proceed to save the user
        User user = userService.save(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getAddressId(), userDTO.getAge());
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.ok("User deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("User not found.");
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred: " + e.getMessage());
    }
}
