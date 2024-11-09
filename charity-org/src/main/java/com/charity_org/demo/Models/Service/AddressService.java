package com.charity_org.demo.Models.Service;
import com.charity_org.demo.Models.Address;
import com.charity_org.demo.Models.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public boolean updateAddressName(Long id, String name) {
        return addressRepository.updateAddressNameById(id, name) == 1;
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }

}