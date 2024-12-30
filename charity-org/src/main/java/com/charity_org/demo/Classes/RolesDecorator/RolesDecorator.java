package com.charity_org.demo.Classes.RolesDecorator;

import com.charity_org.demo.Models.Model.Person;

public abstract class RolesDecorator implements IPerson {
    protected IPerson personRef;
    public abstract void applyRoles();
}
