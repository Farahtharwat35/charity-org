package com.charity_org.demo.Models;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

import com.charity_org.demo.Enums.Roles;
import org.springframework.stereotype.Component;


@Data
@Component
@MappedSuperclass
abstract public class Person extends BaseEntity {
    @Column(nullable = false)
    protected String name;

    @ManyToOne
    @JoinColumn(name = "addressId")
    protected Address address;

    @Column(nullable = false)
    protected String email;
    @Column(nullable = false)
    protected String password;

    protected int age;

    @ElementCollection(targetClass = Roles.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    protected Set<Roles> role = new HashSet<>();

    public abstract void applyRoles();
}
