package com.charity_org.demo.Models;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Setter
@Getter
@NoArgsConstructor
public class FaceToFace extends BaseEntity implements IPaymentMethod{
    //@Column(nullable = false)
    //float currency;
//    @OneToMany(mappedBy = "courier-id")
}
