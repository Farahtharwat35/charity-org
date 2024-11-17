//package com.charity_org.demo;
//
//import com.charity_org.demo.DTO.LoginRequest;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.boot.test.web.server.LocalServerPort;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test") // Optional: to use a specific profile for testing
//public class LoginControllerIntegrationTest {
//
//    @LocalServerPort
//    private int port;  // This will inject the random port assigned to the test
//
//    @Autowired
//    private TestRestTemplate restTemplate;  // Automatically injected by Spring
//
//    @BeforeEach
//    public void setup() {
//        // Any setup logic if needed
//    }
//
//    private String getBaseUrl() {
//        return "http://localhost:" + port + "/login";
//    }
//
//    @Test
//    public void testLoginWithGoogleValidToken() {
//        String url = getBaseUrl() + "?provider=google&token=valid_google_token";
//
//        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
//
//        assertEquals(200, response.getStatusCode().value());
//        assertEquals("Authenticated with google successfully.", response.getBody());
//    }
//
//    @Test
//    public void testLoginWithFacebookValidToken() {
//        String url = getBaseUrl() + "?provider=facebook&token=valid_facebook_token";
//
//        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
//
//        assertEquals(200, response.getStatusCode().value());
//        assertEquals("Authenticated with facebook successfully.", response.getBody());
//    }
//
//    @Test
//    public void testLoginWithInvalidToken() {
//        // Arrange
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setEmail("user@example.com");
//        loginRequest.setPassword("password123");
//        loginRequest.setToken("invalidToken");
//
//        String url = getBaseUrl() + "?provider=someProvider";
//
//        // Act
//        ResponseEntity<String> response = restTemplate.postForEntity(url, loginRequest, String.class);
//
//        // Assert
//        assertEquals(401, response.getStatusCode().value());
//        assertEquals("Authentication failed for someProvider.", response.getBody());
//    }
//
//    @Test
//    public void testLoginWithUnsupportedProvider() {
//        String url = getBaseUrl() + "?provider=twitter&token=some_token";
//
//        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
//
//        assertEquals(400, response.getStatusCode().value());
//        assertEquals("Unsupported provider.", response.getBody());
//    }
//}
