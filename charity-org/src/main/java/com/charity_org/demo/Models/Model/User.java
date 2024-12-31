    package com.charity_org.demo.Models.Model;
    import com.charity_org.demo.Classes.RolesDecorator.IRole;
    import com.charity_org.demo.Enums.Roles;
    import jakarta.persistence.*;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import org.springframework.context.annotation.Primary;
    import org.springframework.stereotype.Component;
    import java.util.Date;
    import java.util.HashSet;
    import java.util.List;
    import java.util.Set;


    @Entity
    @Data
    @NoArgsConstructor
    @Component
    @Primary
    @Table(name = "users")
    public class User extends Person implements IRole {
        @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        private Date visitDate;

        @Column(columnDefinition = "int default 0")
        private int numberOfActionsTaken;

        @OneToMany(mappedBy = "user")
        private List<Donation> donations;

        public User(String name, String email, String password, int age, Address address) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.age = age;
            this.address = address;
        }

        public void applyRoles() {
            this.roles.add(Roles.USER);
        }

//        @ManyToMany(fetch = FetchType.EAGER)
//        @JoinTable(name = "user_roles",
//                joinColumns = @JoinColumn(name = "user_id"),
//                inverseJoinColumns = @JoinColumn(name = "role_id"))

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
        private Set<UserRole> roles = new HashSet<>();

        @Override
        public Set<UserRole> getRoles() {
            return roles;
        }
    }
