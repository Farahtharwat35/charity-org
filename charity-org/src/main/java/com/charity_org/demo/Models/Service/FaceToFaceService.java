package com.charity_org.demo.Models.Service;


import com.charity_org.demo.Models.FaceToFace;
import com.charity_org.demo.Models.IPaymentMethod;
import com.charity_org.demo.Models.VISA;
import com.charity_org.demo.Models.repository.FaceToFaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaceToFaceService implements IPaymentMethodService {
    @Autowired
    FaceToFaceRepository faceRepository;
    @Override
    public boolean processPayment(IPaymentMethod paymentMethod){
        if(!faceRepository.findAll().contains((FaceToFace) paymentMethod)) {
            FaceToFace result = faceRepository.save((FaceToFace) paymentMethod);
        }
        return true;
    }
}
