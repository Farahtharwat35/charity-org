package com.charity_org.demo.Models.Model;

import com.charity_org.demo.Classes.StrategyComponents.IPaymentMethod;
import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Setter
@Getter
@AllArgsConstructor
public class FaceToFace extends BaseEntity implements IPaymentMethod {

}
