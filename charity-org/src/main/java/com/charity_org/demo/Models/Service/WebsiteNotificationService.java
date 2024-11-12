package com.charity_org.demo.Models.Service;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WebsiteNotificationService implements IEventObserver{
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
