package com.charity_org.demo.Models;

import com.charity_org.demo.Enums.Roles;

public class Admin extends RolesDecorator{

    @Override
    public void applyRoles() {
        if (personRef.getRole().contains(Roles.USER)) {
            personRef.getRole().remove(Roles.USER);
        }
        personRef.getRole().add(Roles.ADMIN);
    }
}
