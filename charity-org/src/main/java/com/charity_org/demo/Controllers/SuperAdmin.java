package com.charity_org.demo.Controllers;


import com.charity_org.demo.Middlware.cookies.CookieHandler;
import com.charity_org.demo.Models.Service.SuperAdminService;
import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Models.Service.UserRoleService;
import com.charity_org.demo.Models.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
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

@Service
@Controller()
@RequestMapping("/super_admin")
public class SuperAdmin {
    @Autowired
    private SuperAdminService superAdminService;
    @Autowired
    private UserService userService;
    @Autowired
    CookieHandler cookieHandler;
    @Autowired
    UserRoleService userRoleService;
    @GetMapping("/dashboard")
    public String showDashboard(HttpServletRequest request,Model model) {
        model.addAttribute("user", new User()); // Add User object for form binding
        model.addAttribute("users", superAdminService.getAdmins());
        model.addAttribute("title", "Admins");
        User user= cookieHandler.getUserFromSession(request);
        String name =userRoleService.getRole(request);
        model.addAttribute("role", name);
        model.addAttribute("userID", user.getId());
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
        try {
            if (result.hasErrors()) {
                // If there are validation errors, add an error message and redirect back to the dashboard
                redirectAttributes.addFlashAttribute("error", "Validation failed. Please check the input fields.");
                return "redirect:/super_admin/dashboard";
            }
            User dbUser = userService.getUserByEmail(user.getEmail());
            if (dbUser == null) {
                redirectAttributes.addFlashAttribute("error", "User with this email does not exist.");
                return "redirect:/super_admin/dashboard";
            }
            User createdUser = superAdminService.createAdminUser(dbUser);
            // Add a success message and pass user details to the dashboard
            redirectAttributes.addFlashAttribute("message", "Admin created successfully!");
            redirectAttributes.addFlashAttribute("createdAdmin", createdUser);

            return "redirect:/super_admin/dashboard";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message", "User is already an admin");
            return "redirect:/super_admin/dashboard";
        }
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
        try {
            if (result.hasErrors()) {
                // If there are validation errors, add an error message and redirect back to the dashboard
                redirectAttributes.addFlashAttribute("error", "Validation failed. Please check the input fields.");
                return "redirect:/super_admin/dashboard";
            }
            User dbUser = userService.getUserByEmail(user.getEmail());
            if (dbUser == null) {
                redirectAttributes.addFlashAttribute("error", "User with this email does not exist.");
                return "redirect:/super_admin/dashboard";
            }
            User createdUser = superAdminService.createCourier(dbUser);
            // Add a success message and pass user details to the dashboard
            redirectAttributes.addFlashAttribute("message", "Courier created successfully!");
            redirectAttributes.addFlashAttribute("createdCourier", createdUser);

            return "redirect:/super_admin/list-couriers";
        } catch( Exception e){
            redirectAttributes.addFlashAttribute("message", "User is Already a courier");
            return "redirect:/super_admin/list-couriers";
        }
    }

    @PostMapping ("/deleteAdmin/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        superAdminService.deleteAdmin(id);
        return "redirect:/super_admin/dashboard";
    }

    @PostMapping("/deleteCourier/{id}")
    public String deleteCourier(@PathVariable Long id) {
        superAdminService.deleteCourier(id);
        return "redirect:/super_admin/list-couriers";
    }

    @GetMapping("/list-couriers")
    public String showCourier(Model model){
        model.addAttribute("user", new User()); // Add User object for form binding
        model.addAttribute("users", superAdminService.getCouriers());
        model.addAttribute("title", "Couriers");
        return "superadmin_dashboard"; // Main dashboard view
    }

}
