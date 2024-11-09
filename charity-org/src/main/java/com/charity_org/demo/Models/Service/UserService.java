package com.charity_org.demo.Models.Service;
import com.charity_org.demo.Models.User;
import com.charity_org.demo.Models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
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
        return userRepository.getUserByEmail(password);
    }


}
