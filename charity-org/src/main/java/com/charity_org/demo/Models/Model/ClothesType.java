package com.charity_org.demo.Models.Model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "clothes_types")
@Data
public class ClothesType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;
}
