package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Models.Assigments;
import com.charity_org.demo.Models.Donation;
import com.charity_org.demo.Models.User;
import com.charity_org.demo.Models.repository.AssigmentRepository;
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
        assigmentRepository.getAllByCourier(user.getId());
        return null;
    }
}
