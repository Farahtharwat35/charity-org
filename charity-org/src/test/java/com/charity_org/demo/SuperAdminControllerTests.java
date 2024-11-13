package com.charity_org.demo;

import com.charity_org.demo.Models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.http.HttpMethod;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class SuperAdminControllerTests {

    @Autowired
    protected TestRestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        // Any setup logic if needed
    }


    @Test
    public void testCreateAdminUser() {
        User mockUser = new User();
        mockUser.setName("John Doe");
        mockUser.setEmail("john.doe@example.com");

        String url = "http://localhost:8080/createAdminUser";

        ResponseEntity<User> response = restTemplate.postForEntity(url, mockUser, User.class);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("John Doe", response.getBody().getName());
        assertEquals("john.doe@example.com", response.getBody().getEmail());
    }

    @Test
    public void testDeleteAdmin() {
        Long adminId = 1L;

        String url = "http://localhost:" + restTemplate.getRootUri().split(":")[2] + "/deleteAdmin/" + adminId;

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Admin deleted successfully", response.getBody());
    }
}
