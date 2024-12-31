package com.charity_org.demo.Models.Service;
import com.charity_org.demo.Classes.RolesDecorator.AdminDecorator;
import com.charity_org.demo.Classes.RolesDecorator.SuperAdminDecorator;
import com.charity_org.demo.Models.Model.Role;
import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Models.Model.UserRole;
import com.charity_org.demo.Models.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuperAdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminDecorator adminDecorator;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    public User createAdminUser(User user) {
        AdminDecorator adminDecorator = new AdminDecorator(user ,roleService, userRoleService);
        adminDecorator.applyRole();
        return user;

    }
    public User createSuperAdmin(User user){
        SuperAdminDecorator superAdminDecorator = new SuperAdminDecorator(user, roleService, userRoleService);
        superAdminDecorator.applyRole();
        return user;
    }
    public User createCourier(User user) {
        userRepository.save(user);
        return user;

    }
    public List<User> getAdmins() {
        Role adminRole = roleService.getRoleByName("ROLE_ADMIN");
        List<UserRole> admins = userRoleService.getUsersByRole(adminRole);
        List<User> adminUsers = new ArrayList<>();
        for(UserRole adminUser : admins){
            User admin = userRepository.getById(adminUser.getUser().getId());
            adminUsers.add(admin);
        }
        return adminUsers;
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