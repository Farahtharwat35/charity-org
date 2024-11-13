package com.charity_org.demo.Models.Service.RolesDecorator;
import com.charity_org.demo.Enums.Roles;
import com.charity_org.demo.Models.Person;
import com.charity_org.demo.Models.User;
import com.charity_org.demo.Models.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class SuperAdminService extends RolesDecorator {
    @Autowired
    private UserRepository userRepository;
 @Autowired
    private AdminService adminService;

    @Override
    public void applyRoles(Person personRef) {
        if (!personRef.getRole().contains(Roles.ADMIN)) {
            personRef.getRole().add(Roles.ADMIN);
        }
        if (personRef.getRole().contains(Roles.USER)) {
            personRef.getRole().remove(Roles.USER);
        }
        personRef.getRole().add(Roles.SUPERADMIN);
    }
    public User createUserAdmin(User user) {
       User superAdmin= userRepository.save(user);
        adminService.applyRoles(superAdmin);
        return superAdmin;
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