package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Models.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public void updateAddressName(Long id, String name) {
        addressRepository.updateAddressNameById(id, name);
    }
}
