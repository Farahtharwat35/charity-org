package com.charity_org.demo.Controllers;
import com.charity_org.demo.DTO.SignUpRequest;
import com.charity_org.demo.Models.Model.Address;
import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Models.Model.UserRole;
import com.charity_org.demo.Models.Service.AddressService;
import com.charity_org.demo.Models.Service.RoleService;
import com.charity_org.demo.Models.Service.UserRoleService;
import com.charity_org.demo.Models.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/auth")
public class SignUp {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserRoleService userRoleService;

    // Serve the signup form
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        List<Address> addressesWithoutParent = addressService.findAll().stream()
                .filter(address -> address.getParent() == null)
                .collect(Collectors.toList());
        model.addAttribute("countries", addressesWithoutParent);
        model.addAttribute("signupRequest", new SignUpRequest());
        return "signup";
    }

    // Process the signup form
    @PostMapping("/signup")
    public String signup(
            @Valid SignUpRequest signupRequest,
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
        User user = userService.save(
                signupRequest.getName(),
                signupRequest.getEmail(),
                signupRequest.getPassword(),
                address,
                signupRequest.getAge()
        );

        Set<UserRole> userRoles  = new HashSet<>();
        userRoles.add(userRoleService.createUserRole(user, roleService.getRoleByName("ROLE_USER")));
        user.setRoles(userRoles);

        redirectAttributes.addFlashAttribute("signupRequest", signupRequest);
        return "redirect:/auth/login";
    }

}