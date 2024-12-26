package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Classes.ObserverComponents.IEventObserver;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebsiteNotificationService implements IEventObserver {
    private final int websiteUserId;

    public WebsiteNotificationService(EventService eventService,int websiteUserId) {
        eventService.addObserver(this);
        this.websiteUserId = websiteUserId;
    }

    @Override
    public boolean sendNotification(String subject, String content) {
//        System.out.println("WebsiteNotification: ");
        return true;
    }
}
