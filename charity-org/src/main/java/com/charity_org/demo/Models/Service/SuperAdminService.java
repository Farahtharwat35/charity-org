package com.charity_org.demo.Models.Service;
import com.charity_org.demo.Classes.RolesDecorator.RolesDecorator;
import com.charity_org.demo.Enums.Roles;
import com.charity_org.demo.Models.Model.Person;
import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Models.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperAdminService extends RolesDecorator {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminService adminService;


    public SuperAdminService(Person personRef) {
        this.personRef = personRef;
    }

    @Override
    public void applyRoles() {
       personRef.applyRoles();
       personRef.getRole().add(Roles.SUPERADMIN);
    }
    public User createAdminUser(User user) {
        // Decorate the user with AdminService to apply the ADMIN role
        AdminService adminDecorator = new AdminService(user);
        adminDecorator.applyRoles();
        // Save the updated user
        userRepository.save(user);
        return user;

    }

    public User createCourier(User user) {
        userRepository.save(user);
        return user;

    }
    public List<User> getAdmins() {
        return userRepository.findUsersByRole(Roles.ADMIN);
    }

    @Transactional
    public boolean deleteAdmin(Long adminId) {
        int result = userRepository.deleteUser(adminId);
        return result > 0;
    }

    public boolean deleteCourier(Long courierId) {
        int result = userRepository.deleteUser(courierId);
        return result > 0;
    }
}