package com.charity_org.demo.Classes.Proxy;

import com.charity_org.demo.Classes.CommandComponents.CancelEventCommand;
import com.charity_org.demo.Classes.CommandComponents.CreateEventCommand;
import com.charity_org.demo.Classes.CommandComponents.Invoker;
import com.charity_org.demo.Classes.IteratorComponents.EventIterator;
import com.charity_org.demo.Classes.ObserverComponents.IEventSubject;
import com.charity_org.demo.Classes.Singleton.SingletonLogger;
import com.charity_org.demo.Models.Model.Event;
import com.charity_org.demo.Models.Service.EventService;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class EventServiceProxy implements IEventService {
    private final IEventService target;
    private static final Set<String> blockedIps = ConcurrentHashMap.newKeySet();
    private static final Pattern SQL_INJECTION_PATTERN = Pattern.compile(
            "(?i).*(" +
                    "select\\b.*\\bfrom\\b|" +
                    "union\\b.*\\bselect\\b|" +
                    "drop\\b.*\\btable\\b|" +
                    "insert\\b.*\\binto\\b|" +
                    "delete\\b.*\\bfrom\\b|" +
                    "update\\b.*\\bset\\b|" +
                    "exec\\b|" +
                    "execute\\b|" +
                    "create\\b.*\\btable\\b|" +
                    "--|;--|;|#|\\*|" +
                    "where\\b.*=.*" +
                    ").*",
            Pattern.CASE_INSENSITIVE
    );

    private static final Pattern XSS_PATTERN = Pattern.compile("<script>(.*?)</script>|%3Cscript%3E(.*?)%3C/script%3E", Pattern.CASE_INSENSITIVE);
    private SingletonLogger logger = SingletonLogger.getInstance(SingletonLogger.FileFormat.PLAIN_TEXT);

    public EventServiceProxy(IEventService target) {
        this.target = target;
        logger.log(SingletonLogger.LogLevel.INFO, "EventServiceProxy created");
    }

    private boolean isMalicious(String input) {
        if (input == null) return false;
        boolean sqlMatch = SQL_INJECTION_PATTERN.matcher(input).find();
        boolean xssMatch = XSS_PATTERN.matcher(input).find();
        return sqlMatch || xssMatch;
    }

    private void blockIp(String ip) {
        blockedIps.add(ip);
        logger.log(SingletonLogger.LogLevel.WARN, "IP" + ip + "has been blocked.");
    }

    @Override
    public boolean createEvent(String clientIp, String eventName, Date eventDate, long eventLocationId, String description) {
        logger.log(SingletonLogger.LogLevel.INFO, "Creating event with IP: {}", clientIp);
        if (blockedIps.contains(clientIp)) {
            logger.log(SingletonLogger.LogLevel.WARN, "Blocked IP: {}", clientIp);
            throw new SecurityException("Your IP is blocked.");
        }
        if (isMalicious(eventName) || isMalicious(description)) {
            logger.log(SingletonLogger.LogLevel.WARN, "Malicious content detected in event creation. IP: {}", clientIp);
            blockIp(clientIp);
            throw new SecurityException("Malicious content detected. Your IP has been blocked.");
        }

        Invoker invoker = new Invoker(new CreateEventCommand((EventService) target, eventName, eventDate, eventLocationId, description, clientIp));
        return invoker.executeCommand();
    }

    @Override
    public boolean updateEvent(String clientIp, long id, Event event) {
        logger.log(SingletonLogger.LogLevel.INFO, "Updating event with ID: {} and clientIp {}", id, clientIp);
        if (blockedIps.contains(clientIp)) {
            logger.log(SingletonLogger.LogLevel.WARN, "Attempt to update event with blocked IP: {}", clientIp);
            throw new SecurityException("Your IP is blocked.");
        }
        if (isMalicious(event.getEventName()) || isMalicious(event.getDescription())) {
            logger.log(SingletonLogger.LogLevel.WARN, "Attempt to update event with malicious content. IP: {}", clientIp);
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
        logger.log(SingletonLogger.LogLevel.INFO, "Deleting event with ID: {} and client IP: {}", id, clientIp);
        if (blockedIps.contains(clientIp)) {
            logger.log(SingletonLogger.LogLevel.WARN, "Attempt to delete event with blocked IP: {}", clientIp);
            throw new SecurityException("Your IP is blocked.");
        }
        String subject = "Event Deleted";
        String content = "Event with Name " + getEvent(id).getEventName() + " has been deleted." + "So your registration has been canceled.";
        Invoker invoker = new Invoker(new CancelEventCommand((IEventSubject) target,subject,content , (EventService) target, getEvent(id),clientIp));
        return invoker.executeCommand();
    }

    @Override
    public List<Event> getAllEvents(String clientIp, String queryString) {

        logger.log(SingletonLogger.LogLevel.INFO, "Getting all events with IP: {}", clientIp);
        if (blockedIps.contains(clientIp)) {
            logger.log(SingletonLogger.LogLevel.WARN, "Attempt to get all events with blocked IP: {}", clientIp);
            throw new SecurityException("Your IP is blocked.");
        }
        if (isMalicious(queryString)) {
            logger.log(SingletonLogger.LogLevel.WARN, "Attempt to get all events with blocked IP: {}", clientIp);
            blockIp(clientIp);
            throw new SecurityException("Malicious content detected in query string. Your IP has been blocked.");
        }
        return target.getAllEvents( clientIp, queryString);
    }

    @Override
    public List<Event> listAllUnDeletedEvents(String clientIp, String queryString) {
        logger.log(SingletonLogger.LogLevel.INFO, "Listing all events with IP: {}", clientIp);
        if (blockedIps.contains(clientIp)) {
            logger.log(SingletonLogger.LogLevel.WARN, "Attempt to list all events with blocked IP: {}", clientIp);
            throw new SecurityException("Your IP is blocked.");
        }
        if (isMalicious(queryString)) {
            logger.log(SingletonLogger.LogLevel.WARN, "Attempt to list all events with malicious content. IP: {}", clientIp);
            blockIp(clientIp);
            throw new SecurityException("Malicious content detected in query string. Your IP has been blocked.");
        }
        return target.listAllUnDeletedEvents(clientIp, queryString);
    }

    @Override
    public boolean deleteById(String clientIp, Long id) {
        logger.log(SingletonLogger.LogLevel.INFO, "Deleting event with ID: {} and client IP: {}", id, clientIp);
        if (blockedIps.contains(clientIp)) {
            logger.log(SingletonLogger.LogLevel.WARN, "Attempt to delete event with blocked IP: {}", clientIp);
            throw new SecurityException("Your IP is blocked.");
        }
        String subject = "Event Deleted";
        String content = "Event with Name " + getEvent(id).getEventName() + " has been deleted." + "So your registration has been canceled.";
        Invoker invoker = new Invoker(new CancelEventCommand((IEventSubject) target,subject,content , (EventService) target, getEvent(id),clientIp));
        return invoker.executeCommand();
    }

    @Override
    public EventIterator createSearchIterator(String clientIp, String query, String keyword){
        return target.createSearchIterator(clientIp, query, keyword);
    }
}
