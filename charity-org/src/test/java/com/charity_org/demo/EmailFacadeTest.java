package com.charity_org.demo;
import com.charity_org.demo.Classes.Facade.EmailFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "mailgun.username=postmaster@sandboxdc85e9f2d9d540ad9fd8f2e9201d1274.mailgun.org",
        "mailgun.password=5bfcc4f3c8f43dc0679be2145ba98d59-2e68d0fb-da4064d3"
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
