    package com.charity_org.demo.Models;
    import com.charity_org.demo.Enums.Roles;
    import jakarta.persistence.Entity;
    import jakarta.persistence.OneToMany;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import org.springframework.context.annotation.Primary;
    import org.springframework.stereotype.Component;

    import java.util.Date;
    import java.util.List;

    @Entity
    @Data
    @NoArgsConstructor
    @Component
    @Primary
    public class User extends Person {
        private Date visitDate;
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
            this.role.add(Roles.USER);
        }

    }
