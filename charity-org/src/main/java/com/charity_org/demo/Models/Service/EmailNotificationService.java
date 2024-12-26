package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Classes.ObserverComponents.IEventObserver;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EmailNotificationService implements IEventObserver {
    private final String emailAddress;

    public EmailNotificationService(EventService eventService, String phoneNumber) {
        eventService.addObserver(this);
        this.emailAddress = phoneNumber;
    }

    @Override
    public boolean sendNotification(String subject, String content) {
//        System.out.println("Email Notification: ");

        return true;
    }



}
