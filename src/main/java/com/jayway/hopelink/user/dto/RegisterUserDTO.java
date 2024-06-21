package com.jayway.hopelink.user.dto;

import lombok.Getter;
@Getter
public class RegisterUserDTO {
    private String documentNumber;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String photoUrl;
    private String email;
    private String confirmationEmail;
}
