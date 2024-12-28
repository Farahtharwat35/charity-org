package com.charity_org.demo.Config;

import com.charity_org.demo.Classes.Proxy.EventServiceProxy;
import com.charity_org.demo.Classes.Proxy.IEventService;
import com.charity_org.demo.Middlware.cookies.CookieHandler;
import com.charity_org.demo.Middlware.cookies.SessionRepository;
import com.charity_org.demo.Models.Service.EventService;
import com.charity_org.demo.Models.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
    public class AppConfig {
        @Bean
        public CookieHandler cookieHandler(SessionRepository sessionRepository , UserService userService) {
            return new CookieHandler(sessionRepository, userService);
        }

    @Bean
    public IEventService eventService() {
        return new EventService(); // The actual service
    }

    @Bean
    public IEventService eventServiceProxy(IEventService eventService) {
        return new EventServiceProxy(eventService); // The proxy wrapping the actual service
    }
}

