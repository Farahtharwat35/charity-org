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

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public List<Address> findFullAddress(Long addressId) {
        List<Address> addresses = new ArrayList<>();
        Address address = addressRepository.findById(addressId).orElse(null);
        addresses.add(address);
        Long parentId = address.getParent() != null ? address.getParent().getId() : null;
        while (parentId != null) {
            Address parent = addressRepository.findById(parentId).orElse(null);
            if (parent != null) {
                addresses.add(parent);
                parentId = parent.getParent() != null ? parent.getParent().getId() : null;
            } else {
                break;
            }
        }
        return addresses;
    }

}