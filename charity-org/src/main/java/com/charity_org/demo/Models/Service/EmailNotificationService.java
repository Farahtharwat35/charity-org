package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Classes.Facade.EmailFacade;
import com.charity_org.demo.Classes.ObserverComponents.IEventObserver;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;


@Getter
@Setter
public class EmailNotificationService implements IEventObserver {
    private final String emailAddress;
    private final EmailFacade emailFacade;

    public EmailNotificationService(EventService eventService, String emailAddress) {
        eventService.addObserver(this);
        this.emailAddress = emailAddress;
        this.emailFacade = new EmailFacade();
    }

    @Override
    public boolean sendNotification(String subject, String content) {
        return emailFacade.sendEmail("salmaelsoly@gmail.com", subject, content);
    }



}
