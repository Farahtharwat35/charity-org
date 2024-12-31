package com.charity_org.demo.Classes.RolesDecorator;
import com.charity_org.demo.Models.Model.UserRole;

import java.util.Set;

public abstract class RolesDecoratorr implements IRole {
    protected IRole decoratedRole;

    public RolesDecoratorr(IRole decoratedRole) {
        this.decoratedRole = decoratedRole;
    }

    @Override
    public Set<UserRole> getRoles() {
        return decoratedRole.getRoles();
    }
}
