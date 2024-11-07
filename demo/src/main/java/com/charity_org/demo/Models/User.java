    package com.charity_org.demo.Models;

    import jakarta.persistence.Entity;
    import com.charity_org.demo.Models.repository.UserRepository;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Component;
    import org.springframework.stereotype.Service;

    import java.util.Date;

    @Entity
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public  class User extends Person {


        private Date visitDate;
        private int numberOfActionsTaken;


    }
