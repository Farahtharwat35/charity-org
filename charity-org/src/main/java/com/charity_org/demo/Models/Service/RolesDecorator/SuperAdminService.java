package com.charity_org.demo.Models.Service.RolesDecorator;
import com.charity_org.demo.Enums.Roles;
import com.charity_org.demo.Models.Person;
import com.charity_org.demo.Models.User;
import com.charity_org.demo.Models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public boolean deleteAdmin(Long adminId) {
        return userRepository.deleteUser(adminId);
    }

    public boolean deleteCourier(Long courierId) {
        return userRepository.deleteUser(courierId);
    }
}