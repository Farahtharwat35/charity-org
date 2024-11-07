    package com.charity_org.demo.Models;
    import jakarta.persistence.Entity;
    import lombok.Data;
    import java.util.Date;

    @Entity
    @Data
    public class User extends Person {
        private Date visitDate;
        private int numberOfActionsTaken;
        // private []Event eventsParticipatedIn;
    }
