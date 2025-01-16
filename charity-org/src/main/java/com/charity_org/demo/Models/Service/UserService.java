package com.charity_org.demo.Models.Service;
import com.charity_org.demo.Middlware.cookies.CookieHandler;
import com.charity_org.demo.Models.Model.Address;
import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Models.Repository.AddressRepository;
import com.charity_org.demo.Models.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private AddressService addressService;

    public User save(String name, String email, String password, Address fullAddress, int age) {
        // Save the address and get the saved Address object
        Address address = addressService.save(fullAddress);
        User user = userRepository.save(new User(name, email, password, age, address));
//        user.applyRoles();
        return user;
    }

    public User getUser(long id) {
        return userRepository.getReferenceById(id);
    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public User getUserByEmailAndPassword(String email, String password) {
        return userRepository.getUserByEmailAndPassword(email, password);
    }

    public void updateUserdata(User user) {
        userRepository.updateUserData(user.getId(), user);
        userRepository.save(user);
    }

    public boolean deleteUser(long id) {
        return userRepository.deleteUser(id) > 0 ;
    }}
