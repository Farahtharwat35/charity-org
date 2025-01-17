package com.charity_org.demo.Models.Service;
import com.charity_org.demo.Config.SecurityConfig;
import com.charity_org.demo.Models.Model.Address;
import com.charity_org.demo.Models.Model.Role;
import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Models.Model.UserRole;
import com.charity_org.demo.Models.Repository.AddressRepository;
import com.charity_org.demo.Models.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private AddressService addressService;


    public User save(String name, String email, String password, Address fullAddress, int age) {
        Address address = addressService.save(fullAddress);
        User user = userRepository.save(new User(name, email, password, age, address));
        return user;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        Logger logger = Logger.getLogger(SecurityConfig.class.getName());
        logger.info("Granted Authorities: {}" + getAuthorities(user.getRoles()));
        return new org.springframework.security.core.userdetails.User(
                user.getName(), user.getPassword(), getAuthorities(user.getRoles()));
    }

    public Set<GrantedAuthority> getAuthorities(Set<UserRole> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().getName()))
                .collect(Collectors.toSet());
    }

    public User getUser(long id) {
        return userRepository.getReferenceById(id);
    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public boolean updateUserdata(User user) {
        return userRepository.updateUserData(user.getId(), user) == 1;
    }

    public boolean deleteUser(long id) {
        return userRepository.deleteUser(id) > 0 ;
    }
}
