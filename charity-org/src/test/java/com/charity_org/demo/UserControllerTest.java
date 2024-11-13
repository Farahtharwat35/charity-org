package com.charity_org.demo;

import com.charity_org.demo.DTO.UserDTO;
import com.charity_org.demo.Models.User;
import com.charity_org.demo.Models.Service.RolesDecorator.UserService;
import com.charity_org.demo.Controllers.UserController;
import com.charity_org.demo.Patcher.Patcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private Patcher patcher;

    @Test
    void testUpdateUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("Updated Name");

        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("Original Name");

        when(userService.getUser(userDTO.getId())).thenReturn(existingUser);
        doNothing().when(patcher).objectPatcher(existingUser, userDTO);
        when(userService.updateUserdata(existingUser)).thenReturn(existingUser);

        mockMvc.perform(patch("/users/{id}", userDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userDTO.getId()))
                .andExpect(jsonPath("$.name").value("Updated Name"));

        verify(userService).getUser(userDTO.getId());
        verify(patcher).objectPatcher(existingUser, userDTO);
        verify(userService).updateUserdata(existingUser);
    }

    @Test
    void testUpdateUserNotFound() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(99L); // Non-existent user ID

        when(userService.getUser(userDTO.getId())).thenReturn(null); // Return null to simulate "not found"

        mockMvc.perform(patch("/users/{id}", userDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isNotFound()) // Expecting 404 status
                .andExpect(jsonPath("$.message").value("User not found"));
    }

    @Test
    void testCreateUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("New User");
        userDTO.setEmail("newuser@example.com");
        userDTO.setPassword("password");
        userDTO.setAddressId(123L);
        userDTO.setAge(25);

        User newUser = new User();
        newUser.setId(1L);
        newUser.setName(userDTO.getName());
        newUser.setEmail(userDTO.getEmail());

        when(userService.save(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword(),
                userDTO.getAddressId(), userDTO.getAge())).thenReturn(newUser);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(userDTO.getName()))
                .andExpect(jsonPath("$.email").value(userDTO.getEmail()));


        verify(userService).save(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword(),
                userDTO.getAddressId(), userDTO.getAge());
    }

    @Test
    void testCreateUserInvalidData() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(null);  // Invalid input: missing name
        userDTO.setEmail("test@example.com");  // Valid email
        userDTO.setPassword("password123");    // Valid password
        userDTO.setAge(25);                    // Valid age
        userDTO.setAddressId(1L);              // Valid addressId

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isBadRequest()) // Expecting 400 Bad Request
                .andExpect(jsonPath("$.message").value("Name is required"))
                .andExpect(jsonPath("$.email").value(userDTO.getEmail()))
                .andExpect(jsonPath("$.password").value(userDTO.getPassword()))
                .andExpect(jsonPath("$.age").value(userDTO.getAge()))
                .andExpect(jsonPath("$.addressId").value(userDTO.getAddressId()));
    }


    @Test
    void testDeleteUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        when(userService.deleteUser(userDTO.getId())).thenReturn(true);

        mockMvc.perform(delete("/users/{id}", userDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("User deleted successfully."));

        verify(userService).deleteUser(userDTO.getId());
    }
}