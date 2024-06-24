package com.jayway.hopelink.user.contract.controller;

import com.jayway.hopelink.user.contract.service.UserService;
import com.jayway.hopelink.user.dto.RegisterUserDTO;
import com.jayway.hopelink.user.dto.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_shouldReturnCreatedStatus_whenUserRegisteredSuccessfully() {
        RegisterUserDTO request = RegisterUserDTO.builder()
                .documentNumber("12345678")
                .phoneNumber("912345678")
                .email("test@gmail.com")
                .confirmationEmail("test@gmail.com")
                .lastName("Lastname Test")
                .firstName("Firstname Test")
                .build();
        ResponseEntity<Void> responseEntity = userController.register(request);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        verify(userService, times(1)).register(request);
    }

    @Test
    void findUserByDocumentNumber_shouldReturnUserInfo_whenUserFound() {
        String documentNumber = "12345678";
        UserInfo userInfo = UserInfo.builder().build(); // Mock objeto UserInfo

        when(userService.findByDocumentNumber(documentNumber)).thenReturn(userInfo);

        ResponseEntity<UserInfo> responseEntity = userController.findUserByDocumentNumber(documentNumber);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userInfo, responseEntity.getBody());
    }

}