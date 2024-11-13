    package com.charity_org.demo.Models;
    import jakarta.persistence.Entity;
    import jakarta.persistence.OneToMany;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.util.Date;
    import java.util.List;

    @Entity
    @Data
    @NoArgsConstructor
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

    }
