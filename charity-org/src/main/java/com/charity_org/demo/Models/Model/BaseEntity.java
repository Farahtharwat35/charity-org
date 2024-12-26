package com.charity_org.demo.Models.Model;
import jakarta.persistence.*;
import lombok.Data;


@MappedSuperclass
@Data
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    protected long id;


    @Column(name = "IS_DELETED", nullable = false,columnDefinition = "BOOLEAN DEFAULT false")
    protected boolean isDeleted;
//    private DBconnection dbConnection ; // Will be used
}
