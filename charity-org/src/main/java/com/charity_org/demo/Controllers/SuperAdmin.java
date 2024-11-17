package com.charity_org.demo.Controllers;


import com.charity_org.demo.DTO.SignUpRequest;
import com.charity_org.demo.Models.Service.RolesDecorator.SuperAdminService;
import com.charity_org.demo.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
@Controller()
@RequestMapping("/super_admin")
public class SuperAdmin {
    @Autowired
    private SuperAdminService superAdminService;

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "superadmin_dashboard"; // Name of the HTML file for the dashboard
    }


    @GetMapping("/createAdminUser")
    public String createAdminView(Model model) {
        model.addAttribute("user", new User());
        return "createAdminView"; // Admin creation form view
    }

    @PostMapping("/createAdminUser")
    public String createAdminUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        User createdUser = superAdminService.createAdminUser(user);

        // Add a success message and pass user details to the dashboard
        redirectAttributes.addFlashAttribute("message", "Admin created successfully!");
        redirectAttributes.addFlashAttribute("createdAdmin", createdUser);

        return "redirect:/super_admin/dashboard";
    }


    @GetMapping("/createCourierView")
    public String createCourierView(Model model) {
        model.addAttribute("user", new User());
        return "createCourierView";
    }
    @PostMapping("/createCourier")
    public String createCourier(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        User createdUser = superAdminService.createCourier(user);

        // Add a success message and pass user details to the dashboard
        redirectAttributes.addFlashAttribute("message", "Courier created successfully!");
        redirectAttributes.addFlashAttribute("createdCourier", createdUser);

        return "redirect:/super_admin/dashboard";
    }

    @DeleteMapping("/deleteAdmin/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
        boolean isDeleted = superAdminService.deleteAdmin(id);
        if (isDeleted) {
            return ResponseEntity.ok("Admin deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Admin not found");
        }
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
