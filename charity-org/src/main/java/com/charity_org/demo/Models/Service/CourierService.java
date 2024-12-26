package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Models.Model.Assigments;
import com.charity_org.demo.Models.Model.Donation;
import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Models.Repository.AssigmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourierService {

    @Autowired
    private AssigmentRepository assigmentRepository;

    public void assignCourierToDonation(User user, Donation donation) {
        assigmentRepository.save(new Assigments(user, donation));
    }

    public List<Assigments> getMyAssigments(User user) {
        return assigmentRepository.getAllByCourier(1);
    }
}
