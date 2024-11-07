package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Models.Address;
import com.charity_org.demo.Models.repository.SuperAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperAdminService {
    @Autowired
    private SuperAdminRepository superAdminRepository;

    public boolean updateSuperAdminName(Long id, String name) {
        return superAdminRepository.updateSuperAdminNameById(id, name) == 1;
    }

    public void createAdmin(String name, Address address, String email, String password, int age) {
        // Implementation here
    }

    public void createCourier(String name, Address address, String email, String password, int age, String phoneNumber) {
        // Implementation here
    }

    public void deleteAdmin(Long adminId) {
        // Implementation here
    }

    public void deleteCourier(Long courierId) {
        // Implementation here
    }
}