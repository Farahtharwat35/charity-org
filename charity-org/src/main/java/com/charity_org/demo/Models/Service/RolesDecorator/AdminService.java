package com.charity_org.demo.Models.Service.RolesDecorator;

import com.charity_org.demo.Enums.Roles;
import com.charity_org.demo.Models.Person;
import com.charity_org.demo.Models.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends RolesDecorator {
    @Autowired
    private EventService eventService;

    @Override
    public void applyRoles(Person personRef) {
        if (personRef.getRole().contains(Roles.USER)) {
            personRef.getRole().remove(Roles.USER);
        }
        personRef.getRole().add(Roles.ADMIN);
    }

}
