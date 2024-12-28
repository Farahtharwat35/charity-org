package com.charity_org.demo.Classes.TemplateComponents;

public abstract class UserRegistration {
    public final void registerUser() {
        validateUserInfo();
        registerSpecificUser();
        sendWelcomeEmail();
    }

    private void validateUserInfo() {
        System.out.println("Validating user info...");
    }

    protected abstract void registerSpecificUser();

    private void sendWelcomeEmail() {
        System.out.println("Sending welcome email...");
    }
}