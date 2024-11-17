package com.charity_org.demo.Models.Service;


import com.charity_org.demo.Enums.PaymentStatus;
import com.charity_org.demo.Models.FaceToFace;
import com.charity_org.demo.Models.PaymentMethod;
import com.charity_org.demo.Models.VISA;
import com.charity_org.demo.Models.repository.FaceToFaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaceToFaceService implements IPaymentMethodService {
    @Autowired
    FaceToFaceRepository faceRepository;
    @Override
    public boolean processPayment(PaymentMethod paymentMethod){
        if(!faceRepository.findAll().contains((FaceToFace) paymentMethod)) {
            FaceToFace result = faceRepository.save((FaceToFace) paymentMethod);
        }
        paymentMethod.setStatus(PaymentStatus.COMPLETED);
        return true;
    }
}
