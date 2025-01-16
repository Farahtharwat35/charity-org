package com.charity_org.demo.Models.Service;
import com.charity_org.demo.Classes.AdapterComponents.IAddressTarget;
import com.charity_org.demo.Classes.AdapterComponents.UniversalTutorialAddressAdaptee;
import com.charity_org.demo.Classes.AdapterComponents.UniversalTutorialAddressAdapter;
import com.charity_org.demo.Models.Model.Address;
import com.charity_org.demo.Models.Repository.AddressRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;


    @Autowired
    private UniversalTutorialAddressAdapter universalTutorialAddressAdapter;


    @PostConstruct
    public void initialize() {
        saveAddressesFromAdapter();
    }

    public boolean updateAddressName(Long id, String name) {
        return addressRepository.updateAddressNameById(id, name) == 1;
    }

    public Address createAddress(String address) {
        Address newAddress = new Address();
        newAddress.setName(address);
        return addressRepository.save(newAddress);
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

    public void saveAddressesFromAdapter(){
       List<IAddressTarget> addressAdapters = new ArrayList<>();
        addressAdapters.add(universalTutorialAddressAdapter);
        for (IAddressTarget addressAdapter : addressAdapters) {
            List<Address> countries = addressAdapter.getCountries();
            addressRepository.saveAll(countries);
            for (Address country : countries) {
                List<Address> states = addressAdapter.getStates(country);
                if (states != null && !states.isEmpty()) {
                    addressRepository.saveAll(states);
                }
            }
    }
    }

    public List<Address> getCitiesByParentId(Long id){
        return addressRepository.getAddressesByParentId(id);
    }
}