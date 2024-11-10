package com.charity_org.demo.Models;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

import com.charity_org.demo.Enums.Roles;

@Entity
@Data
abstract public class Person extends BaseEntity {
    @Column(nullable = false)
    protected String name;

    @ManyToOne
    @JoinColumn(name = "addressId")
    private Address address;

    @Column(nullable = false)
    protected String email;
    @Column(nullable = false)
    protected String password;

    protected int age;

    @ElementCollection(targetClass = Roles.class)
    @Enumerated(EnumType.STRING)
    protected Set<Roles> role = new HashSet<>(Collections.singletonList(Roles.USER));
}
