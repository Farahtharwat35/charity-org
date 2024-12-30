package com.charity_org.demo.Models.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity {
    @Column(unique = true)
    private String name;
}