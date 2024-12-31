package com.charity_org.demo.Classes.RolesDecorator;
import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Models.Model.UserRole;
import com.charity_org.demo.Models.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class SuperAdminDecorator implements IRole {
    private IRole decoratedRole;

    public SuperAdminDecorator(IRole decoratedRole) {
        this.decoratedRole = decoratedRole;
    }

    @Autowired
    RoleService roleService;

    @Override
    public Set<UserRole> applyRole() {
        User user = (User) this.decoratedRole;
        Set<UserRole> roles = user.getRoles();
        UserRole superAdminRole = new UserRole();
        superAdminRole.setUser(user);
        superAdminRole.setRole(roleService.getRoleByName("ROLE_SUPERADMIN"));
        roles.add(superAdminRole);
        return roles;
    }
}
