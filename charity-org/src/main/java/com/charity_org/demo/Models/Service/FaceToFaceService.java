package com.charity_org.demo.Models.Service;


import com.charity_org.demo.Classes.TemplateComponents.IPaymentMethodService;
import com.charity_org.demo.Models.Model.FaceToFace;
import com.charity_org.demo.Classes.StrategyComponents.IPaymentMethod;
import com.charity_org.demo.Models.Repository.FaceToFaceRepository;
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
