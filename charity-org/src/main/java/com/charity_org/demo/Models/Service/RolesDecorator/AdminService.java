package com.charity_org.demo.Models.Service.RolesDecorator;

import com.charity_org.demo.Enums.Roles;
import com.charity_org.demo.Models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends RolesDecorator {

    @Autowired
    public AdminService(Person personRef) {
        this.personRef = personRef;
    }

    @Override
    public void applyRoles() {
        personRef.applyRoles();
        personRef.getRole().add(Roles.ADMIN);
    }

}
