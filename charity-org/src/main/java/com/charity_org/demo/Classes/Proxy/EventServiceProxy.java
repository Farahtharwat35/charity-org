package com.charity_org.demo.Classes.Proxy;

import com.charity_org.demo.Models.Model.Event;


import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class EventServiceProxy implements IEventService {
    private final IEventService target;
    private static final Set<String> blockedIps = ConcurrentHashMap.newKeySet();
    private static final Pattern SQL_INJECTION_PATTERN = Pattern.compile("(?i)(select\\s+.*from|union\\s+.*select|drop\\s+table|insert\\s+into|delete\\s+from|update\\s+.*set|--|;--|;|#)\n");
    private static final Pattern XSS_PATTERN = Pattern.compile("<script>(.*?)</script>|%3Cscript%3E(.*?)%3C/script%3E", Pattern.CASE_INSENSITIVE);

    public EventServiceProxy(IEventService target) {
        this.target = target;
        System.out.println("EventServiceProxy has been injected!");
    }

    private boolean isMalicious(String input) {
        if (input == null) return false;
        boolean match = SQL_INJECTION_PATTERN.matcher(input).find();
        return match;
    }

    private void blockIp(String ip) {
        blockedIps.add(ip);
        System.err.println("Blocked IP: " + ip);
    }

    @Override
    public boolean createEvent(String clientIp, String eventName, Date eventDate, long eventLocationId, String description) {
        if (blockedIps.contains(clientIp)) {
            throw new SecurityException("Your IP is blocked.");
        }
        if (isMalicious(eventName) || isMalicious(description)) {
            blockIp(clientIp);
            throw new SecurityException("Malicious content detected. Your IP has been blocked.");
        }
        return target.createEvent(clientIp, eventName, eventDate, eventLocationId, description);
    }

    @Override
    public boolean createEvent(String clientIp, Event event) {
        if (blockedIps.contains(clientIp)) {
            throw new SecurityException("Your IP is blocked.");
        }
        if (isMalicious(event.getEventName()) || isMalicious(event.getDescription())) {
            blockIp(clientIp);
            throw new SecurityException("Malicious content detected. Your IP has been blocked.");
        }
        return target.createEvent(clientIp, event);
    }

    @Override
    public boolean updateEvent(String clientIp, long id, Event event) {
        if (blockedIps.contains(clientIp)) {
            throw new SecurityException("Your IP is blocked.");
        }
        if (isMalicious(event.getEventName()) || isMalicious(event.getDescription())) {
            blockIp(clientIp);
            throw new SecurityException("Malicious content detected. Your IP has been blocked.");
        }
        return target.updateEvent(clientIp, id, event);
    }

    @Override
    public Event getEvent(long id) {
        return target.getEvent(id);
    }

    @Override
    public boolean deleteEvent(String clientIp, long id) {
        if (blockedIps.contains(clientIp)) {
            throw new SecurityException("Your IP is blocked.");
        }
        return target.deleteEvent(clientIp, id);
    }

    @Override
    public List<Event> getAllEvents() {
        return target.getAllEvents();
    }

    @Override
    public List<Event> listAllEvents() {
        return target.listAllEvents();
    }

    @Override
    public boolean deleteById(String clientIp, Long id) {
        if (blockedIps.contains(clientIp)) {
            throw new SecurityException("Your IP is blocked.");
        }
        return target.deleteById(clientIp, id);
    }
}
