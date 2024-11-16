package com.charity_org.demo.Models;
import com.charity_org.demo.Enums.Roles;
import lombok.Data;

@Data
public class SuperAdmin extends RolesDecorator {
    @Override
    public void applyRoles() {
        if (!personRef.getRole().contains(Roles.ADMIN)) {
            personRef.getRole().add(Roles.ADMIN);
        }
        if (personRef.getRole().contains(Roles.USER)) {
            personRef.getRole().remove(Roles.USER);
        }
        personRef.getRole().add(Roles.SUPERADMIN);
    }
}