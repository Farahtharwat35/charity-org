package com.charity_org.demo;
import com.charity_org.demo.Classes.Facade.EmailFacade;
import org.junit.jupiter.api.Test;

public class EmailFacadeTest {
    @Test
    public void testSendEmail() {
        EmailFacade emailFacade = EmailFacade.getInstance();
        boolean result = emailFacade.sendEmail("salmaelsoly@gmail.com", "Test Subject", "Test Content");
        assert result;
    }
}
