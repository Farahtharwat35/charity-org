package com.charity_org.demo.Models;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.*;

import com.charity_org.demo.Enums.Roles;

@Entity
@Data
abstract public class Person extends BaseEntity {
    //private Donnation donnation;
    protected String name;
    protected int addressId ;
    protected String email;
    protected String password;
    protected int age;

    @ElementCollection(targetClass = Roles.class)
    @Enumerated(EnumType.STRING)
    protected Set<Roles> role = new HashSet<>(Collections.singletonList(Roles.USER));
}
