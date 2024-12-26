package com.charity_org.demo.Classes.ObserverComponents;

public interface IEventSubject {
    public boolean addObserver(IEventObserver observer);
    public boolean removeObserver(IEventObserver observer);
    public boolean notifyObserver(String subject,String content);
}
