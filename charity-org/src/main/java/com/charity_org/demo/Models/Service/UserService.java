package com.charity_org.demo.Models.Service;
import com.charity_org.demo.Classes.RolesDecorator.IPerson;
import com.charity_org.demo.Models.Model.Address;
import com.charity_org.demo.Models.Model.Role;
import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Models.Repository.AddressRepository;
import com.charity_org.demo.Models.Repository.RolesRepository;
import com.charity_org.demo.Models.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService, IPerson {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    RolesRepository rolesRepository;

    @Override
    public void applyRoles(){
        Role role = rolesRepository.findByName("USER");
    }

    public User save(String name, String email, String password, Address fullAddress, int age) {
        // Save the address and get the saved Address object
        Address address = addressService.save(fullAddress);
        User user = userRepository.save(new User(name, email, password, age, address));
        return user;
    }

    public User getUser(long id) {
        return userRepository.getReferenceById(id);
    }

    private Set<GrantedAuthority> getAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toSet());
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);
        if(user == null){
           throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), getAuthorities(user.getRoles()));
    }

    public User getUserByEmail(String email) {
        return  userRepository.getUserByEmail(email);
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
    }


}