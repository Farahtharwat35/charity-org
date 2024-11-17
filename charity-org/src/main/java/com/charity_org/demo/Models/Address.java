package com.charity_org.demo.Models;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Data
public class Address extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parentId")
    private Address parent;

    @OneToMany(mappedBy = "parent")
    private List<Address> children;
}

