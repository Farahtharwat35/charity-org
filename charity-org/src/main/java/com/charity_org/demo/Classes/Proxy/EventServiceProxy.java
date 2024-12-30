package com.charity_org.demo.Classes.Proxy;

import com.charity_org.demo.Models.Model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class EventServiceProxy implements IEventService {
    private final IEventService target;
    private static final Set<String> blockedIps = ConcurrentHashMap.newKeySet();
    private static final Pattern SQL_INJECTION_PATTERN = Pattern.compile("(?i)(select\\s+.*from|union\\s+.*select|drop\\s+table|insert\\s+into|delete\\s+from|update\\s+.*set|--|;--|;|#)\n", Pattern.CASE_INSENSITIVE);
    private static final Pattern XSS_PATTERN = Pattern.compile("<script>(.*?)</script>|%3Cscript%3E(.*?)%3C/script%3E", Pattern.CASE_INSENSITIVE);
    private static final Logger logger = LoggerFactory.getLogger(EventServiceProxy.class);
    public EventServiceProxy(IEventService target) {
        this.target = target;
        logger.info("EventServiceProxy created");
    }

    private boolean isMalicious(String input) {
        if (input == null) return false;
        boolean sqlMatch = SQL_INJECTION_PATTERN.matcher(input).find();
        boolean xssMatch = XSS_PATTERN.matcher(input).find();
        return sqlMatch || xssMatch;
    }

    private void blockIp(String ip) {
        blockedIps.add(ip);
        logger.warn("IP {} has been blocked.", ip);
    }

    @Override
    public boolean createEvent(String clientIp, String eventName, Date eventDate, long eventLocationId, String description) {
        logger.info("Creating event with IP: {}", clientIp);
        if (blockedIps.contains(clientIp)) {
            logger.warn("Attempt to create event with blocked IP: {}", clientIp);
            throw new SecurityException("Your IP is blocked.");
        }
        if (isMalicious(eventName) || isMalicious(description)) {
            logger.warn("Malicious content detected in event creation. IP: {}", clientIp);
            blockIp(clientIp);
            throw new SecurityException("Malicious content detected. Your IP has been blocked.");
        }
        return target.createEvent(clientIp, eventName, eventDate, eventLocationId, description);
    }

    @Override
    public boolean createEvent(String clientIp, Event event) {
        logger.info("Creating event with IP: {}", clientIp);
        if (blockedIps.contains(clientIp)) {
            logger.warn("Attempt to create event with blocked IP: {}", clientIp);
            throw new SecurityException("Your IP is blocked.");
        }
        if (isMalicious(event.getEventName()) || isMalicious(event.getDescription())) {
            logger.warn("Attempt to create event with malicious content. IP: {}", clientIp);
            blockIp(clientIp);
            throw new SecurityException("Malicious content detected. Your IP has been blocked.");
        }
        return target.createEvent(clientIp, event);
    }

    @Override
    public boolean updateEvent(String clientIp, long id, Event event) {
        logger.info("Updating event with IP: {}", clientIp);
        if (blockedIps.contains(clientIp)) {
            logger.warn("Attempt to update event with blocked IP: {}", clientIp);
            throw new SecurityException("Your IP is blocked.");
        }
        if (isMalicious(event.getEventName()) || isMalicious(event.getDescription())) {
            logger.warn("Attempt to update event with malicious content. IP: {}", clientIp);
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
        logger.info("Deleting event with IP: {}", clientIp);
        if (blockedIps.contains(clientIp)) {
            logger.warn("Attempt to delete event with blocked IP: {}", clientIp);
            throw new SecurityException("Your IP is blocked.");
        }
        return target.deleteEvent(clientIp, id);
    }

    @Override
    public List<Event> getAllEvents(String clientIp, String queryString) {
        logger.info("Getting all events with IP: {}", clientIp);
        if (blockedIps.contains(clientIp)) {
            logger.warn("Attempt to get all events with blocked IP: {}", clientIp);
            throw new SecurityException("Your IP is blocked.");
        }
        if (isMalicious(queryString)) {
            logger.warn("Attempt to get all events with malicious content. IP: {}", clientIp);
            blockIp(clientIp);
            throw new SecurityException("Malicious content detected in query string. Your IP has been blocked.");
        }
        return target.getAllEvents( clientIp, queryString);
    }

    @Override
    public List<Event> listAllUnDeletedEvents(String clientIp, String queryString) {
        logger.info("Listing all events with IP: {}", clientIp);
        if (blockedIps.contains(clientIp)) {
            logger.warn("Attempt to list all events with blocked IP: {}", clientIp);
            throw new SecurityException("Your IP is blocked.");
        }
        if (isMalicious(queryString)) {
            logger.warn("Attempt to list all events with malicious content. IP: {}", clientIp);
            blockIp(clientIp);
            throw new SecurityException("Malicious content detected in query string. Your IP has been blocked.");
        }
        return target.listAllUnDeletedEvents(clientIp, queryString);
    }

    @Override
    public boolean deleteById(String clientIp, Long id) {
        logger.info("Deleting event with IP: {}", clientIp);
        if (blockedIps.contains(clientIp)) {
            logger.warn("Attempt to delete event with blocked IP: {}", clientIp);
            throw new SecurityException("Your IP is blocked.");
        }
        return target.deleteById(clientIp, id);
    }
}
