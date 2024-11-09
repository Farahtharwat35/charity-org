package com.charity_org.demo.Models;
import jakarta.persistence.*;
import lombok.Data;


@MappedSuperclass
@Data
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    protected long id;
    protected boolean isDeleted;
//    private DBconnection dbConnection ; // Will be used
}
