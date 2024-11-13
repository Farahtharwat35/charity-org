package com.charity_org.demo.Models.Service;

public interface IEventSubject {
    public boolean addObserver(IEventObserver observer);
    public boolean removeObserver(IEventObserver observer);
    public boolean notifyObserver(String subject,String content);
}
