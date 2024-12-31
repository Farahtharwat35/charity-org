package com.charity_org.demo.Models.Service;
import com.charity_org.demo.Classes.RolesDecorator.AdminDecorator;
import com.charity_org.demo.Classes.RolesDecorator.CourierDecorator;
import com.charity_org.demo.Classes.RolesDecorator.SuperAdminDecorator;
import com.charity_org.demo.Models.Model.Role;
import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Models.Model.UserRole;
import com.charity_org.demo.Models.Repository.UserRepository;
import com.charity_org.demo.Models.Repository.UserRoleRepository;
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
        CourierDecorator courierDecorator = new CourierDecorator(user, roleService, userRoleService);
        courierDecorator.applyRole();
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

    public List<User> getCouriers() {
        Role courierRole = roleService.getRoleByName("ROLE_COURIER");
        List<UserRole> courier = userRoleService.getUsersByRole(courierRole);
        List<User> courierUsers = new ArrayList<>();
        for(UserRole courierUser : courier){
            User admin = userRepository.getById(courierUser.getUser().getId());
            courierUsers.add(admin);
        }
        return courierUsers;
    }


    @Transactional
    public boolean deleteAdmin(Long adminId) {
        return userRoleService.deleteAdmin(adminId) > 0;
    }

    public boolean deleteCourier(Long courierId) {
       return userRoleService.deleteCourier(courierId) > 0;
    }
}