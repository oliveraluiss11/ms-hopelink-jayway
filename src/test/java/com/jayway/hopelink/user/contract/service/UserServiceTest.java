package com.jayway.hopelink.user.contract.service;

import com.jayway.hopelink.user.contract.document.UserDocument;
import com.jayway.hopelink.user.contract.repository.UserRepository;
import com.jayway.hopelink.user.dto.RegisterUserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterValidUser() {
        // Arrange
        RegisterUserDTO validUser = new RegisterUserDTO(
                "12345678",
                "912345678",
                "John",
                "Doe",
                "john.doe@gmail.com",
                "john.doe@gmail.com");

        // Act
        userService.register(validUser);

        // Assert
        ArgumentCaptor<UserDocument> userCaptor = ArgumentCaptor.forClass(UserDocument.class);
        verify(userRepository, times(1)).save(userCaptor.capture());
        var savedUser = userCaptor.getValue();

        assertNotNull(savedUser);
        assertEquals(validUser.getDocumentNumber(), savedUser.getDocumentNumber());
        assertEquals(validUser.getPhoneNumber(), savedUser.getPhoneNumber());
        assertEquals(validUser.getFirstName(), savedUser.getFirstName());
        assertEquals(validUser.getLastName(), savedUser.getLastName());
        assertEquals(validUser.getEmail(), savedUser.getEmail());
    }

    @Test
    void testRegisterInvalidUser() {
        // Arrange
        RegisterUserDTO invalidUser = new RegisterUserDTO(
                "1234",
                "912345678",
                "John",
                "Doe",
                "john.doe@gmail.com",
                "john.doe@gmail.com");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> userService.register(invalidUser));
        verify(userRepository, times(0)).save(any());
    }
}