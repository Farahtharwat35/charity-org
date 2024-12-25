package com.charity_org.demo.Middlware.cookies;
import com.charity_org.demo.Models.Service.RolesDecorator.UserService;
import com.charity_org.demo.Models.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class CookieHandler {

    private final SessionRepository sessionRepository;
    private final UserService userService;

    @Autowired
    public CookieHandler(SessionRepository sessionRepository, UserService userService) {
        this.sessionRepository = sessionRepository;
        this.userService = userService;
    }

    public String getCookieValue(String cookieName, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            return Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals(cookieName))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    public User getUserFromSession(String sessionId) {
        Session session = sessionRepository.findBySessionId(sessionId);
        if (session != null && session.getUserId() != null) {
            return userService.getUser(session.getUserId());
        }
        return null;
    }

    public void setCookie(String cookieName, String cookieValue, int maxAge, HttpServletResponse response, HttpServletRequest request, String url, Long userId) {
        if (url == null) {
            url = request.getRequestURL().toString();
        }

        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        sessionRepository.save(Session.builder()
                .sessionId(cookieValue)
                .cookieName(cookieName)
                .url(url)
                .userId(userId)
                .build());
    }

    public void removeCookie(String cookieName, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    cookie.setValue("");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    Session session = sessionRepository.findBySessionId(cookie.getValue());
                    if (session != null) {
                        sessionRepository.delete(session);
                    }
                }
            }
        }
    }

    public boolean cookieExists(String cookieName, HttpServletRequest request) {
        return getCookieValue(cookieName, request) != null;
    }


    public User getUserFromSession(String cookieValue, HttpServletRequest request) {
        Session session = sessionRepository.findBySessionId(cookieValue);
        Long userID = session.getUserId();
        return userService.getUser(userID);
    }


}
