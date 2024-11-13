package com.charity_org.demo.Models.Service.RolesDecorator;
import com.charity_org.demo.Enums.Roles;
import com.charity_org.demo.Models.Person;
import com.charity_org.demo.Models.User;
import com.charity_org.demo.Models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService extends RolesDecorator {
    @Autowired
    UserRepository userRepository;

    public User save(User user) {
       return userRepository.save(user);
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

    public User getUserByPassword(String email, String password){
        return userRepository.getUserByPassword(password);
    }

    public User getUserByEmailAndPassword(String email, String password){
        return userRepository.getUserByEmailAndPassword(email, password);
    }
    @Override
    public void applyRoles(Person person) {
       person.setRole(Collections.singleton(Roles.USER));
    }
}
