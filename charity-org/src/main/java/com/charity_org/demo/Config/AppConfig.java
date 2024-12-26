package com.charity_org.demo.Config;

import com.charity_org.demo.Middlware.cookies.CookieHandler;
import com.charity_org.demo.Middlware.cookies.SessionRepository;
import com.charity_org.demo.Models.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
    public class AppConfig {
        @Bean
        public CookieHandler cookieHandler(SessionRepository sessionRepository , UserService userService) {
            return new CookieHandler(sessionRepository, userService);
        }
    }

