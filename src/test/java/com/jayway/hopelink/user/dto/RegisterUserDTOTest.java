package com.jayway.hopelink.user.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterUserDTOTest {
    @Test
    void testValidUser() {
        RegisterUserDTO user = new RegisterUserDTO(
                "12345678",
                "912345678",
                "John",
                "Doe",
                "john.doe@gmail.com",
                "john.doe@gmail.com");

        assertDoesNotThrow(user::ensureAttributes);
    }

    @Test
    void testInvalidDocumentNumber() {
        RegisterUserDTO user = new RegisterUserDTO(
                "1234",
                "912345678",
                "John",
                "Doe",
                "john.doe@gmail.com",
                "john.doe@gmail.com");

        Exception exception = assertThrows(IllegalArgumentException.class, user::ensureAttributes);
        assertEquals("Document number is not valid", exception.getMessage());
    }

    @Test
    void testInvalidPhoneNumber() {
        RegisterUserDTO user = new RegisterUserDTO(
                "12345678",
                "12345678",
                "John",
                "Doe",
                "john.doe@gmail.com",
                "john.doe@gmail.com");

        Exception exception = assertThrows(IllegalArgumentException.class, user::ensureAttributes);
        assertEquals("Phone number is not valid", exception.getMessage());
    }

    @Test
    void testInvalidEmailFormat() {
        RegisterUserDTO user = new RegisterUserDTO(
                "12345678",
                "912345678",
                "John",
                "Doe",
                "john.doe@invalid.com",
                "john.doe@invalid.com");

        Exception exception = assertThrows(IllegalArgumentException.class, user::ensureAttributes);
        assertEquals("Email is not valid", exception.getMessage());
    }

    @Test
    void testNonMatchingEmails() {
        RegisterUserDTO user = new RegisterUserDTO(
                "12345678",
                "912345678",
                "John",
                "Doe",
                "john.doe@gmail.com",
                "john.different@gmail.com");

        Exception exception = assertThrows(IllegalArgumentException.class, user::ensureAttributes);
        assertEquals("Emails do not match", exception.getMessage());
    }

    @Test
    void testInvalidNameFormat() {
        RegisterUserDTO user = new RegisterUserDTO(
                "12345678",
                "912345678",
                "John1",
                "Doe",
                "john.doe@gmail.com",
                "john.doe@gmail.com");

        Exception exception = assertThrows(IllegalArgumentException.class, user::ensureAttributes);
        assertEquals("Name or Lastname is not valid", exception.getMessage());
    }

}