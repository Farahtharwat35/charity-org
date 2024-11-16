package com.charity_org.demo.Models.Service.RolesDecorator;
import com.charity_org.demo.Models.User;
import com.charity_org.demo.Models.repository.AddressRepository;
import com.charity_org.demo.Models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;

    public User save(String name, String email, String password, long addressId, int age) {
         User user = userRepository.save( new User( name, email, password, age, addressRepository.getReferenceById(addressId)));
         user.applyRoles();
         return user;

    }

    public User getUser(long id) {
        return userRepository.getReferenceById(id);
    }

    public long getCount(){
        return userRepository.count();
    }


    public User getUserByEmailAndPassword(String email, String password){
        return userRepository.getUserByEmailAndPassword(email, password);
    }
}
