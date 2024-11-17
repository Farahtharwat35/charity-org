//package com.charity_org.demo.Unit;
//
//import com.charity_org.demo.Controllers.AdminController;
//import com.charity_org.demo.DTO.PostOrPutEventRequest;
//import com.charity_org.demo.Enums.EventStatus;
//import com.charity_org.demo.Models.Event;
//import com.charity_org.demo.Models.Service.EventService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.*;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//
//
//import java.util.Date;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//class AdminControllerTest {
//
//    @Mock
//    private EventService eventService;
//
//    @InjectMocks
//    private AdminController adminController;
//
//    @Mock
//    private PostOrPutEventRequest mockRequest;
//
//
//    @Mock
//    private ModelMapper modelMapper;
//
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//        mockRequest = new PostOrPutEventRequest();
//        mockRequest.setEventName("Test Event");
//        mockRequest.setEventDate(new Date());
//        mockRequest.setEventLocationId(1L);
//        mockRequest.setDescription("Test Description");
//
//        Event mockEvent = new Event("Test Event", new Date(), null, "Test Description", EventStatus.UPCOMING);
//
//        adminController = new AdminController(eventService,modelMapper);
//    }
//
//    @Test
//    void createEvent_ShouldReturnOk_WhenEventIsCreatedSuccessfully() {
//        // Arrange
//        when(eventService.createEvent(anyString(), any(), anyLong(), anyString())).thenReturn(true);
//
//        BindingResult bindingResult = mock(BindingResult.class);
//        when(bindingResult.hasErrors()).thenReturn(false);
//
//        // Act
//        ResponseEntity<?> response = adminController.createEvent(mockRequest, bindingResult);
//
//        // Assert
//        assertEquals(200, response.getStatusCodeValue());
//        assertTrue((Boolean) response.getBody());
//        verify(eventService, times(1)).createEvent(anyString(), any(), anyLong(), anyString());
//    }
//
//    @Test
//    void createEvent_ShouldReturnBadRequest_WhenValidationFails() {
//        // Arrange
//        BindingResult bindingResult = mock(BindingResult.class);
//        when(bindingResult.hasErrors()).thenReturn(true);
//        when(bindingResult.getAllErrors()).thenReturn(java.util.Collections.singletonList(new org.springframework.validation.ObjectError("field", "Event name is required.")));
//
//        // Act
//        ResponseEntity<?> response = adminController.createEvent(mockRequest, bindingResult);
//
//        // Assert
//        assertEquals(400, response.getStatusCodeValue());
//        assertEquals("Event name is required.", response.getBody());
//    }
//
//    @Test
//    public void deleteEvent_ShouldReturnOk_WhenEventIsDeleted() {
//        // Arrange: Simulate successful deletion
//        long eventId = 1L;
//        when(eventService.deleteEvent(eventId)).thenReturn(true);
//
//        // Act: Call the deleteEvent method in AdminController
//        ResponseEntity<Object> response = adminController.deleteEvent(eventId);
//
//        // Assert: Verify that the response is a success with the expected message
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals("Event deleted successfully", response.getBody());
//    }
//
//    // Test case for event not found scenario
//    @Test
//    public void deleteEvent_ShouldReturnBadRequest_WhenEventNotFound() {
//        // Arrange: Simulate failure (event not found)
//        long eventId = 1L;
//        when(eventService.deleteEvent(eventId)).thenReturn(false);
//
//        // Act: Call the deleteEvent method in AdminController
//        ResponseEntity<Object> response = adminController.deleteEvent(eventId);
//
//        // Assert: Verify that the response is a bad request with the error message
//        assertEquals(400, response.getStatusCodeValue());
//        assertEquals("Event not found", response.getBody());
//    }
//
//}
