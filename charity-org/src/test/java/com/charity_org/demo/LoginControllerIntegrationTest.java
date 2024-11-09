package com.charity_org.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // Optional: to use a specific profile for testing
public class LoginControllerIntegrationTest {

    @Autowired
    protected TestRestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        // Any setup logic if needed
    }

    @Test
    public void testLoginWithGoogleValidToken() {
        String url = "http://localhost:8080/login?provider=google&token=valid_google_token";

        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Authenticated with google successfully.", response.getBody());
    }

    @Test
    public void testLoginWithFacebookValidToken() {
        String url = "http://localhost:8080/login?provider=facebook&token=valid_facebook_token";

        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Authenticated with facebook successfully.", response.getBody());
    }

    @Test
    public void testLoginWithInvalidToken() {
        String url = "http://localhost:8080/login?provider=google&token=invalid_google_token";

        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);

        assertEquals(401, response.getStatusCode().value());
        assertEquals("Authentication failed for google.", response.getBody());
    }

    @Test
    public void testLoginWithUnsupportedProvider() {
        String url = "http://localhost:8080/login?provider=twitter&token=some_token";

        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("Unsupported provider.", response.getBody());
    }
}
