package com.charity_org.demo.Classes.AdapterComponents;

import java.util.ArrayList;
import java.util.List;
import com.charity_org.demo.Models.Model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;


@Component
public class UniversalTutorialAddressAdapter implements IAddressTarget {

    @Autowired
    private UniversalTutorialAddressAdaptee adaptee;

    public UniversalTutorialAddressAdapter(UniversalTutorialAddressAdaptee universalTutorialAddressAdaptee) {
        this.adaptee = universalTutorialAddressAdaptee;
    }

    public List<Address> getCountries(){
        List<Address> countries = new ArrayList<>();
        HttpResponse<JsonNode> response = adaptee.getCountries();
        if (response.isSuccess()){
            for (int i = 0; i < response.getBody().getArray().length(); i++) {
                String countryName = response.getBody().getArray().getJSONObject(i).getString("country_name");
                countries.add(new Address(countryName, null , null));
            }
            return countries;
        }
        return countries;
    }
    public List<Address> getStates(Address country){
        List<Address> states = new ArrayList<>();
        HttpResponse<JsonNode> response = adaptee.getStates(country);
        if (response.isSuccess()){
            for (int i = 0; i < response.getBody().getArray().length(); i++) {
                String stateName = response.getBody().getArray().getJSONObject(i).getString("state_name");
                states.add(new Address(stateName, country , null));
            }
            return states;
        }
        return states;
    }
}
