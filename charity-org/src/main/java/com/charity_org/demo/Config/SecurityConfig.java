package com.charity_org.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**", "/home/**").hasRole("USER")
                .requestMatchers("/super_admin/**").hasRole("SUPER_ADMIN")
                .requestMatchers("/courier/**").hasRole("COURIER")
                .requestMatchers("/auth/login", "/auth/logout", "/auth/signup").permitAll()
        );

        return http.build();
    }
}
