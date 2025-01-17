package com.charity_org.demo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf((AbstractHttpConfigurer::disable))
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
                    .requestMatchers("/user/**").hasAuthority("ROLE_USER")
                    .requestMatchers("/super_admin/**").hasAuthority("ROLE_SUPER_ADMIN")
                    .requestMatchers("/courier/**").hasAuthority("ROLE_COURIER")
                    .requestMatchers("/auth/logout", "/auth/signup", "/h2-console/**", "/events/cities**").permitAll()
                )
                .formLogin((form) -> form
                        .loginPage("/auth/login")
                        .successHandler(customAuthenticationSuccessHandler)
                        .permitAll()
                );


        // Enable H2 Console Access
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }
}
