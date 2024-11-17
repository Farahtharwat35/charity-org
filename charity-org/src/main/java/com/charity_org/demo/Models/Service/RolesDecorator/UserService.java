package com.charity_org.demo.Models.Service.RolesDecorator;
import com.charity_org.demo.Models.Address;
import com.charity_org.demo.Models.User;
import com.charity_org.demo.Models.repository.AddressRepository;
import com.charity_org.demo.Models.Service.AddressService;
import com.charity_org.demo.Models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;

    public User save(String name, String email, String password, Address fullAddress, int age) {
        // Save the address and get the saved Address object
        Address address = addressService.save(fullAddress);
         User user = userRepository.save( new User( name, email, password, age, address));
         user.applyRoles();
        return user;

    }

    public User getUser(long id) {
        return userRepository.getReferenceById(id);
    }

    public long getCount(){
        return userRepository.count();
    }

    public User getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

    public User getUserByEmailAndPassword(String email, String password){
        return userRepository.getUserByEmailAndPassword(email, password);
    }
}
