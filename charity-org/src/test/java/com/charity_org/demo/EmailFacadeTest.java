package com.charity_org.demo;
import com.charity_org.demo.Classes.Facade.EmailFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "mailgun.username=",
        "mailgun.password="
})
public class EmailFacadeTest {
    @Autowired
    private EmailFacade emailFacade;
    @Test
    public void testSendEmail() {
        boolean result = emailFacade.sendEmail("salmaelsoly@gmail.com", "Test Subject", "Test Content");
        assert result;
    }
}
