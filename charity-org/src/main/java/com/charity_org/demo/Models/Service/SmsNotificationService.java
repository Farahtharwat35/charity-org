package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Classes.ObserverComponents.IEventObserver;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsNotificationService implements IEventObserver {
    private final String phoneNumber;

    public SmsNotificationService(EventService eventService,String phoneNumber) {
        eventService.addObserver(this);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean sendNotification(String subject, String content) {
//        System.out.println("SMS Notification: ");
        return true;
    }

}
