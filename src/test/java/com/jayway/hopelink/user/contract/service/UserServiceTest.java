package com.jayway.hopelink.user.contract.service;

import com.jayway.hopelink.user.contract.document.UserDocument;
import com.jayway.hopelink.user.contract.repository.UserRepository;
import com.jayway.hopelink.user.dto.RegisterUserDTO;
import com.jayway.hopelink.user.dto.UserInfo;
import com.jayway.hopelink.user.exception.AlreadyUserExistsException;
import com.jayway.hopelink.user.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

        when(userRepository.findByDocumentNumberOrPhoneNumberOrEmail(
                validUser.getDocumentNumber(),
                validUser.getPhoneNumber(),
                validUser.getEmail()))
                .thenReturn(Optional.empty());

        // Act
        userService.register(validUser);

        // Assert
        ArgumentCaptor<UserDocument> userCaptor = ArgumentCaptor.forClass(UserDocument.class);
        verify(userRepository, times(1)).save(userCaptor.capture());
        UserDocument savedUser = userCaptor.getValue();

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

    @Test
    void testRegisterUserAlreadyExists() {
        // Arrange
        RegisterUserDTO existingUser = new RegisterUserDTO(
                "12345678",
                "912345678",
                "John",
                "Doe",
                "john.doe@gmail.com",
                "john.doe@gmail.com");

        var user = new UserDocument();
        user.setDocumentNumber(existingUser.getDocumentNumber());
        user.setPhoneNumber(existingUser.getPhoneNumber());
        user.setEmail(existingUser.getEmail());

        when(userRepository.findByDocumentNumberOrPhoneNumberOrEmail(
                existingUser.getDocumentNumber(),
                existingUser.getPhoneNumber(),
                existingUser.getEmail()))
                .thenReturn(Optional.of(user));

        // Act & Assert
        assertThrows(AlreadyUserExistsException.class, () -> userService.register(existingUser));
        verify(userRepository, times(0)).save(any());
    }

    @Test
    void findByDocumentNumber_shouldThrowException_whenUserNotFound() {
        String documentNumber = "12345678";

        when(userRepository.findByDocumentNumber(documentNumber)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findByDocumentNumber(documentNumber));
    }

    @Test
    void findByDocumentNumber_shouldReturnUserInfo_whenUserFound() {
        String documentNumber = "12345678";
        var user = new UserDocument();
        user.setDocumentNumber(documentNumber);

        when(userRepository.findByDocumentNumber(documentNumber)).thenReturn(Optional.of(user));

        UserInfo userInfo = userService.findByDocumentNumber(documentNumber);
        assertNotNull(userInfo);
        assertEquals(documentNumber, userInfo.getDocumentNumber());
    }

    @Test
    void findByDocumentNumber_shouldThrowIllegalArgumentException_whenDocumentNumberIsInvalid() {
        String invalidDocumentNumber = "123ABC";

        assertThrows(IllegalArgumentException.class, () -> userService.findByDocumentNumber(invalidDocumentNumber));
    }
}