package com.charity_org.demo.Models;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Setter
@Getter
@AllArgsConstructor
public class FaceToFace extends BaseEntity implements IPaymentMethod {

}
