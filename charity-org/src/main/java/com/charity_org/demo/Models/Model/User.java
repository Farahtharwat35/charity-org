package com.charity_org.demo.Models.Model;

import com.charity_org.demo.Classes.RolesDecorator.IRole;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User extends Person implements IRole {

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date visitDate = new Date();

    @Column(nullable = false, columnDefinition = "int default 0")
    private int numberOfActionsTaken;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Donation> donations;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> roles = new HashSet<>();

    public User(String name, String email, String password, int age, Address address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.address = address;
    }

    @Override
    public Set<UserRole> applyRole() {
        // Add role application logic here if needed
        return roles;
    }
}
