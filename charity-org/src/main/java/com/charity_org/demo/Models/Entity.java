package com.charity_org.demo.Models;
import jakarta.persistence.*;
import lombok.Data;


@MappedSuperclass
@Data
public abstract class Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean isDeleted;
//    private DBconnection dbConnection ; // Will be used
}
