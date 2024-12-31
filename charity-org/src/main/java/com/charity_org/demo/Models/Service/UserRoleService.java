package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Models.Model.Role;
import com.charity_org.demo.Models.Model.UserRole;
import com.charity_org.demo.Models.Repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.charity_org.demo.Models.Model.User;

import java.util.List;


@Service
public class UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;

    public UserRole createUserRole(User user, Role role){
       return userRoleRepository.save(new UserRole(user, role));
    }
    List<UserRole> getRolesByUser(User user){
        return userRoleRepository.getUserRolesByUser(user.getId());
    }

    List<UserRole> getUsersByRole(Role role){
        return userRoleRepository.getUserRolesByRole(role.getId());
    }

    int deleteAdmin(long adminId){
        return userRoleRepository.deleteUserRoleByUserIdAndRole(adminId, "ROLE_ADMIN");
    }

    int deleteCourier(long courierId){
        return userRoleRepository.deleteUserRoleByUserIdAndRole(courierId, "ROLE_COURIER");
    }

}
