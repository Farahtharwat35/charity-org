package com.charity_org.demo;
import com.charity_org.demo.Controllers.SuperAdmin;
import com.charity_org.demo.Models.Service.RolesDecorator.SuperAdminService;
import com.charity_org.demo.Models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

public class SuperAdminControllerTests {

    @Mock
    private SuperAdminService superAdminService;

    @InjectMocks
    private SuperAdmin superAdminController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
    }

    @Test
    public void testCreateAdminUser() {
        // Arrange
        User mockUser = new User();
        mockUser.setName("John Doe");
        mockUser.setEmail("john.doe@example.com");
        when(superAdminService.createUserAdmin(any(User.class))).thenReturn(mockUser);
        // Act
        ResponseEntity<User> response = superAdminController.createAdminUser(mockUser);
        // Assert
        assertEquals(OK, response.getStatusCode());
        assertEquals("John Doe", response.getBody().getName());
        assertEquals("john.doe@example.com", response.getBody().getEmail());

        verify(superAdminService, times(1)).createUserAdmin(any(User.class));
    }

    @Test
    public void testCreateCourier() {
        // Arrange
        User mockCourier = new User();
        mockCourier.setName("Jane Smith");
        mockCourier.setEmail("jane.smith@example.com");

        when(superAdminService.createCourier(any(User.class))).thenReturn(mockCourier);

        // Act
        ResponseEntity<User> response = superAdminController.createCourier(mockCourier);

        // Assert
        assertEquals(OK, response.getStatusCode());
        assertEquals("Jane Smith", response.getBody().getName());
        assertEquals("jane.smith@example.com", response.getBody().getEmail());

        verify(superAdminService, times(1)).createCourier(any(User.class));
    }

    @Test
    public void testDeleteAdminSuccess() {
        // Arrange
        Long adminId = 1L;
        when(superAdminService.deleteAdmin(adminId)).thenReturn(true);

        // Act
        ResponseEntity<String> response = superAdminController.deleteAdmin(adminId);

        // Assert
        assertEquals(OK, response.getStatusCode());
        assertEquals("Admin deleted successfully", response.getBody());

        verify(superAdminService, times(1)).deleteAdmin(adminId);
    }

    @Test
    public void testDeleteAdminNotFound() {
        // Arrange
        Long adminId = 1L;
        when(superAdminService.deleteAdmin(adminId)).thenReturn(false);

        // Act
        ResponseEntity<String> response = superAdminController.deleteAdmin(adminId);

        // Assert
        assertEquals(NOT_FOUND, response.getStatusCode());
        assertEquals("Admin not found", response.getBody());

        verify(superAdminService, times(1)).deleteAdmin(adminId);
    }

    @Test
    public void testDeleteCourierSuccess() {
        // Arrange
        Long courierId = 1L;
        when(superAdminService.deleteCourier(courierId)).thenReturn(true);

        // Act
        ResponseEntity<String> response = superAdminController.deleteCourier(courierId);

        // Assert
        assertEquals(OK, response.getStatusCode());
        assertEquals("Courier deleted successfully", response.getBody());

        verify(superAdminService, times(1)).deleteCourier(courierId);
    }

    @Test
    public void testDeleteCourierNotFound() {
        // Arrange
        Long courierId = 1L;
        when(superAdminService.deleteCourier(courierId)).thenReturn(false);

        // Act
        ResponseEntity<String> response = superAdminController.deleteCourier(courierId);

        // Assert
        assertEquals(NOT_FOUND, response.getStatusCode());
        assertEquals("Courier not found", response.getBody());

        verify(superAdminService, times(1)).deleteCourier(courierId);
    }
}
