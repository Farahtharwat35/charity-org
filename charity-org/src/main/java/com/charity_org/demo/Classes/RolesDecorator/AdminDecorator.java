package com.charity_org.demo.Classes.RolesDecorator;
import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Models.Model.UserRole;
import com.charity_org.demo.Models.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class AdminDecorator extends RolesDecoratorr{

    public AdminDecorator(IRole decoratedRole) {
        super(decoratedRole);
    }

    @Autowired
    RoleService roleService;

    @Override
    public Set<UserRole> getRoles() {
        Set<UserRole> roles = super.getRoles();
        UserRole adminRole = new UserRole();
        User user = (User) this.decoratedRole;
        adminRole.setUser(user);
        adminRole.setRole(roleService.getRoleByName("ADMIN"));
        roles.add(adminRole);
        return roles;
    }


}
