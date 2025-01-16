package com.charity_org.demo.Classes.AdapterComponents;

import java.util.List;
import com.charity_org.demo.Models.Model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressAdapter implements IAddressTarget {
    @Autowired
    private AddressAdaptee adaptee;

    public List<Address> getCountries(){
         return adaptee.getCountries();
    }
    public List<Address> getStates(Address country){
        return  adaptee.getStates(country);
    }
}
