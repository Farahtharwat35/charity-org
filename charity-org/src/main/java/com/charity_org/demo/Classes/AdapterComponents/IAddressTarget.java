package com.charity_org.demo.Classes.AdapterComponents;

import com.charity_org.demo.Models.Model.Address;

import java.util.List;

public interface IAddressTarget {

    List<Address> getCountries();
    List<Address> getStates(Address country);
}
