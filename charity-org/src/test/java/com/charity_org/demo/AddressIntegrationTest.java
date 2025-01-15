package com.charity_org.demo;

import com.charity_org.demo.Classes.AdapterComponents.AddressAdaptee;
import com.charity_org.demo.Classes.AdapterComponents.AddressAdapter;
import com.charity_org.demo.Models.Model.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
        "address.api.key=i7GAL7Y6Ry8xMdDmYfzPb8RXP7Ur5-Sbk84RD2tjZ3DlwbTTX2xuFtMZmatpd9g_eyA",
        "address.email=salmaelsoly@gmail.com"
})
public class AddressIntegrationTest {
    @Autowired
    AddressAdapter addressAdapter;
    @Test
    public void testGetCountries() {
        List<Address> countries = addressAdapter.getCountries();

        assertNotNull(countries, "The countries list should not be null.");
        assertFalse(countries.isEmpty(), "The countries list should not be empty.");
        System.out.println("Countries: ");
        countries.forEach(country -> System.out.println(country.getName()));
    }

    @Test
    public void testGetStates() {
        Address country = new Address("Egypt", null, null); // Example country
        List<Address> states = addressAdapter.getStates(country);

        assertNotNull(states, "The states list should not be null.");
        assertFalse(states.isEmpty(), "The states list should not be empty.");
        System.out.println("States in " + country.getName() + ": ");
        states.forEach(state -> System.out.println(state.getName()));
    }
}