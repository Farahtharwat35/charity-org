package com.charity_org.demo.Controllers;

import com.charity_org.demo.Models.Service.RolesDecorator.SuperAdminService;
import com.charity_org.demo.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Service
@RestController
@RequestMapping("/api/superadmin")
public class SuperAdmin {
    @Autowired
    private SuperAdminService superAdminService;

    @PostMapping("/createadminuser")
    public ResponseEntity<User> createAdminUser(@RequestBody User user) {
        User createdUser = superAdminService.createUserAdmin(user);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/createCourier")
    public ResponseEntity<User> createCourier(@RequestBody User user) {
        User createdCourier = superAdminService.createCourier(user);
        return ResponseEntity.ok(createdCourier);
    }

    @DeleteMapping("/deleteadmin/{id}")
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
