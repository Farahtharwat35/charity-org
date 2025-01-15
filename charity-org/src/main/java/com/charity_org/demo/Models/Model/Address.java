package com.charity_org.demo.Models.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parentId")
    @JsonBackReference // Prevents serialization of the parent
    private Address parent;

    @OneToMany(mappedBy = "parent")
    @JsonManagedReference // Serializes only the children
    private List<Address> children;
}
