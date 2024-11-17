package com.charity_org.demo.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class FaceToFace extends BaseEntity implements IPaymentMethod{
    //@Column(nullable = false)
    //float currency;
//    @OneToMany(mappedBy = "courier-id")
    @ManyToOne
    @JoinColumn(name = "courier-id")
    private String courierId;

}
