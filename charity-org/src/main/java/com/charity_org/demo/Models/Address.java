package com.charity_org.demo.Models;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import jakarta.persistence.Entity;

import java.util.List;


@Entity
@Data
public class Address extends BaseEntity {
    private String name;

    @ManyToOne
    @JoinColumn(name = "parentId")
    private Address parent;

    @OneToMany(mappedBy = "parent")
    private List<Address> children;
}

