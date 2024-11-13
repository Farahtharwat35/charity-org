    package com.charity_org.demo.Models;
    import jakarta.persistence.Entity;
    import jakarta.persistence.OneToMany;
    import lombok.Data;
    import java.util.Date;
    import java.util.List;

    @Entity
    @Data
    public class User extends Person {
        private Date visitDate;
        private int numberOfActionsTaken;
        @OneToMany(mappedBy = "user")
        private List<Donation> donations;


    }
