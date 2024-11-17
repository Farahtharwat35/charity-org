package com.charity_org.demo.Controllers;


import com.charity_org.demo.DTO.SignUpRequest;
import com.charity_org.demo.Models.Service.RolesDecorator.SuperAdminService;
import com.charity_org.demo.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Service
@Controller()
@RequestMapping("/super_admin")
public class SuperAdmin {
    @Autowired
    private SuperAdminService superAdminService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("user", new User()); // Add User object for form binding
        model.addAttribute("admins", superAdminService.getAdmins());
        return "superadmin_dashboard"; // Main dashboard view
    }

//    @GetMapping("/createAdminUser")
//    public String createAdminView(Model model) {
//        model.addAttribute("user", new User());
//        return "createAdminView"; // Admin creation form view
//    }

    @PostMapping("/createAdminUser")
    public String createAdminUser(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // If there are validation errors, add an error message and redirect back to the dashboard
            redirectAttributes.addFlashAttribute("error", "Validation failed. Please check the input fields.");
            return "redirect:/super_admin/dashboard";
        }
        User createdUser = superAdminService.createAdminUser(user);
        // Add a success message and pass user details to the dashboard
        redirectAttributes.addFlashAttribute("message", "Admin created successfully!");
        redirectAttributes.addFlashAttribute("createdAdmin", createdUser);

        return "redirect:/super_admin/dashboard";
    }


//    @GetMapping("/createCourierView")
//    public String createCourierView(Model model) {
//        model.addAttribute("user", new User());
//        return "createCourierView";
//    }
    @PostMapping("/createCourier")
    public String createCourier(   @Valid @ModelAttribute("user") User user,
                                   BindingResult result,
                                   RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // If there are validation errors, add an error message and redirect back to the dashboard
            redirectAttributes.addFlashAttribute("error", "Validation failed. Please check the input fields.");
            return "redirect:/super_admin/dashboard";
        }
        User createdUser = superAdminService.createCourier(user);

        // Add a success message and pass user details to the dashboard
        redirectAttributes.addFlashAttribute("message", "Courier created successfully!");
        redirectAttributes.addFlashAttribute("createdCourier", createdUser);

        return "redirect:/super_admin/dashboard";
    }



    @PostMapping ("/deleteAdmin/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        superAdminService.deleteAdmin(id);
        return "redirect:/super_admin/dashboard";
    }

    @DeleteMapping("/deleteCourier/{id}")
    public ResponseEntity<String> deleteCourier(@PathVariable Long id) {
        boolean isDeleted = superAdminService.deleteCourier(id);
        if (isDeleted) {
            return ResponseEntity.ok("Courier deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Courier not found");
        }
    }

}
