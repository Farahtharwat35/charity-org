package com.charity_org.demo.Classes.RolesDecorator;

import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Models.Model.UserRole;
import com.charity_org.demo.Models.Service.RoleService;
import com.charity_org.demo.Models.Service.UserRoleService;

import java.util.Set;

public class CourierDecorator extends RolesDecoratorr{
    private RoleService roleService;
    private UserRoleService userRoleService;

    public CourierDecorator(IRole decoratedRole , RoleService roleService , UserRoleService userRoleService) {
        super(decoratedRole);
        this.roleService = roleService;
        this.userRoleService  = userRoleService;
    }
    @Override
    public Set<UserRole> applyRole() {
        User user = (User) this.decoratedRole;
        Set<UserRole> roles = user.getRoles();
        UserRole role = userRoleService.createUserRole(user,roleService.getRoleByName("ROLE_COURIER"));
        roles.add(role);
        return roles;
    }
}
