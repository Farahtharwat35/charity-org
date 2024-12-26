package com.charity_org.demo.Classes.RolesDecorator;

import com.charity_org.demo.Models.Model.Person;

public abstract class RolesDecorator extends Person {
    protected Person personRef;
    public abstract void applyRoles();
}
