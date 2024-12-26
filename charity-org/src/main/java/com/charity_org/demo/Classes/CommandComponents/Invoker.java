package com.charity_org.demo.Classes.CommandComponents;

public class Invoker {
    private EventCommand command;

    public Invoker(EventCommand command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}
