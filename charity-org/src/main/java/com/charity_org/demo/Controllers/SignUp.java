package com.charity_org.demo.Controllers;

import com.charity_org.demo.DTO.SignUpRequest;
import com.charity_org.demo.Models.Address;
import com.charity_org.demo.Models.Service.AddressService;
import com.charity_org.demo.Models.Service.RolesDecorator.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.groups.Default;


@Controller
@RequestMapping("/auth")
public class SignUp {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;
    // Serve the signup form
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("signupRequest", new SignUpRequest());
        return "signup";
    }

    // Process the signup form
    @PostMapping("/signup")
    public String signup(
            @ModelAttribute("signupRequest") @Validated({SignUpRequest.Create.class, Default.class}) SignUpRequest signupRequest,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        // Check if the email is already taken
        if (userService.getUserByEmail(signupRequest.getEmail()) != null) {
            model.addAttribute("error", "User already exists with this email.");
            return "signup";
        }

        // Parse and validate the address
        Address address = addressService.createAddress(signupRequest.getAddress());
        if (address == null) {
            model.addAttribute("error", "Invalid address provided.");
            return "signup"; // If the address is invalid, return to the signup form with error
        }

        // Save the user and associate the address
        userService.save(
                signupRequest.getName(),
                signupRequest.getEmail(),
                signupRequest.getPassword(), // Be sure to hash the password in production
                address, // Pass the full address object, not just the address ID
                signupRequest.getAge()
        );

        redirectAttributes.addFlashAttribute("signupRequest", signupRequest);
        // Redirect to a success page (or login page)
        return "redirect:/auth/success";
    }

    // Serve a success page
    @GetMapping("/success")
    public String signupSuccess(Model model) {
        if (model.containsAttribute("signupRequest")) {
            System.out.println("Signup data: " + model.getAttribute("signupRequest"));
        } else {
            System.out.println("No signupRequest data available in model");
        }
        return "signup_success";
    }

}