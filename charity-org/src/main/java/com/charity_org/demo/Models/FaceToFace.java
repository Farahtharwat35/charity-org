package com.charity_org.demo.Models;

import jakarta.persistence.Column;

public class FaceToFace extends BaseEntity implements IPaymentMethod{
    @Column(nullable = false)
    float currency;

}
