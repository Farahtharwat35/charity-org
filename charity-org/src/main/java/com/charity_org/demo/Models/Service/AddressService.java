package com.charity_org.demo.Models.Service;
import com.charity_org.demo.Models.Address;
import com.charity_org.demo.Models.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public boolean updateAddressName(Long id, String name) {
        return addressRepository.updateAddressNameById(id, name) == 1;
    }

    public Address createAddress(String address) {
        // If the address doesn't exist, create a new one
        Address newAddress = new Address();
        newAddress.setName(address);
        return addressRepository.save(newAddress); // Save and return the new address
    }
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public List<Address> findFullAddress(Long addressId) {
        List<Address> addresses = new ArrayList<>();
        Address currentAddress = addressRepository.findById(addressId).orElse(null);

        while (currentAddress != null) {
            addresses.add(currentAddress);
            currentAddress = currentAddress.getParent() != null
                    ? addressRepository.findById(currentAddress.getParent().getId()).orElse(null)
                    : null;
        }
        return addresses;
    }

    public List<Address> findAll(){
        return addressRepository.findAll();
    }
}