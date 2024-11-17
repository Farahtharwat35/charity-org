package com.charity_org.demo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Courier extends Person {

    @OneToMany(mappedBy = "courier")
    List<FaceToFace> ordersList;

}
