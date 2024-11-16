package com.charity_org.demo.Models.Service.RolesDecorator;

import com.charity_org.demo.Models.Person;

public abstract class RolesDecorator extends Person {
    protected Person personRef;
    public abstract void applyRoles();
}
