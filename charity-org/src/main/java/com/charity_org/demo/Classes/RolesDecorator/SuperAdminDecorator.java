package com.charity_org.demo.Classes.RolesDecorator;
import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Models.Model.UserRole;
import com.charity_org.demo.Models.Service.RoleService;
import com.charity_org.demo.Models.Service.UserRoleService;
import java.util.Set;



public class SuperAdminDecorator extends RolesDecoratorr {

    private IRole decoratedRole;

    RoleService roleService;
    UserRoleService userRoleService;

    public SuperAdminDecorator(IRole decoratedRole ,  RoleService roleService , UserRoleService userRoleService) {
        super(decoratedRole);
        this.decoratedRole = decoratedRole;
        this.roleService = roleService;
        this.userRoleService = userRoleService;
    }

    @Override
    public Set<UserRole> applyRole() {
        User user = (User) this.decoratedRole;
        Set<UserRole> roles = user.getRoles();
        UserRole superAdminRole = userRoleService.createUserRole(user,roleService.getRoleByName("SUPERADMIN"));
        roles.add(superAdminRole);
        return roles;
    }
}
