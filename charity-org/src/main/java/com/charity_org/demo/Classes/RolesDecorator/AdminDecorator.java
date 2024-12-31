package com.charity_org.demo.Classes.RolesDecorator;
import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Models.Model.UserRole;
import com.charity_org.demo.Models.Service.RoleService;
import com.charity_org.demo.Models.Service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;

@Component
public class AdminDecorator extends RolesDecoratorr {

    private RoleService roleService;
    private UserRoleService userRoleService;

    public AdminDecorator(IRole decoratedRole , RoleService roleService , UserRoleService userRoleService) {
        super(decoratedRole);
        this.roleService = roleService;
        this.userRoleService  = userRoleService;
    }
    @Override
    public Set<UserRole> applyRole() {
        User user = (User) this.decoratedRole;
        Set<UserRole> roles = user.getRoles();
        UserRole role = userRoleService.createUserRole(user,roleService.getRoleByName("ROLE_ADMIN"));
        roles.add(role);
        return roles;
    }


}
