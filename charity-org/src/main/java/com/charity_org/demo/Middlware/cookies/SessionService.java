package com.charity_org.demo.Middlware.cookies;
import com.charity_org.demo.Models.Service.RolesDecorator.UserService;
import com.charity_org.demo.Models.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    CookieHandler cookieHandlerService;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private UserService userService;


    public String getCookieValueByName(String cookieName, HttpServletRequest request) {
        return cookieHandlerService.getCookieValue(cookieName , request);
    }


    public User getUserFromSession(String cookieValue, HttpServletRequest request) {
        Session session = sessionRepository.findBySessionId(cookieValue);
        Long userID = session.getUserId();
        return userService.getUser(userID);
    }
}
