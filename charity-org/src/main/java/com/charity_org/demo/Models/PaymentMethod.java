package com.charity_org.demo.Models;


import com.charity_org.demo.Enums.PaymentStatus;

public abstract class PaymentMethod extends BaseEntity{
    protected PaymentStatus status;

    protected PaymentMethod(){
        this.status = PaymentStatus.PENDING;
    }
}
