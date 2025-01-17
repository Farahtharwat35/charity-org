package com.charity_org.demo.Config;
import com.charity_org.demo.Models.Model.Role;
import com.charity_org.demo.Models.Model.UserRole;
import com.charity_org.demo.Models.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.charity_org.demo.Models.Model.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManagerBuilder auth;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        try {
            configure(auth , user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getName(), user.getPassword(), getAuthorities(user.getRoles()));
    }

    private Set<GrantedAuthority> getAuthorities(Set<UserRole> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().getName()))
                .collect(Collectors.toSet());
    }


    protected void configure(AuthenticationManagerBuilder auth , User user) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(user.getName()).password("{noop}"+ user.getPassword()).roles("USER");
    }
}