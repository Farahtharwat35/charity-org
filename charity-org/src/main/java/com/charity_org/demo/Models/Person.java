package com.charity_org.demo.Models;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

import com.charity_org.demo.Enums.Roles;

@Entity
@Data
abstract public class Person extends BaseEntity {
    //private Donnation donnation;
    @Column(nullable = false)
    protected String name;
    @Column(nullable = false)
    protected long addressId ;
    @Column(nullable = false)
    protected String email;
    @Column(nullable = false)
    protected String password;

    protected int age;

    @ElementCollection(targetClass = Roles.class)
    @Enumerated(EnumType.STRING)
    protected Set<Roles> role = new HashSet<>(Collections.singletonList(Roles.USER));
}
