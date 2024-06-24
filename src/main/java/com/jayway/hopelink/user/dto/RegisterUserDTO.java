package com.jayway.hopelink.user.dto;

import com.jayway.hopelink.user.contract.document.UserDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Optional;
@AllArgsConstructor
@Getter
@Builder
public class RegisterUserDTO {
    private String documentNumber;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String confirmationEmail;

    public void ensureAttributes(){
        ensureValidNameFormat(this.firstName);
        ensureValidNameFormat(this.lastName);
        ensureDocumentNumberIsValid(this.documentNumber);
        ensureValidEmailFormat(this.email);
        ensureValidEmailFormat(this.confirmationEmail);
        ensureValidEmailWithConfirmationEmail(this.email, this.confirmationEmail);
        ensurePhoneNumberIsValid(this.phoneNumber);
    }

    public void ensureDocumentNumberIsValid(String value){
        Optional.ofNullable(value)
                .filter(e -> e.matches("[0-9]{8}"))
                .orElseThrow(()-> new IllegalArgumentException("Document number is not valid"));
    }
    public void ensurePhoneNumberIsValid(String value){
        Optional.ofNullable(value)
                .filter(e -> e.matches("9[0-9]{8}"))
                .orElseThrow(()-> new IllegalArgumentException("Phone number is not valid"));
    }

    public void ensureValidNameFormat(String value) {
        Optional.ofNullable(value)
                .filter(e -> e.matches("[\\p{L} '-]+"))
                .orElseThrow(()-> new IllegalArgumentException("Name or Lastname is not valid"));
    }

    public void ensureValidEmailFormat(String value) {
        if (!value.matches("^[a-zA-Z0-9._%+-]+@(gmail|hotmail|outlook)\\.(com|es|org)$")) {
            throw new IllegalArgumentException("Email is not valid");
        }
    }

    public void ensureValidEmailWithConfirmationEmail(String email, String confirmationEmail){
        Optional.ofNullable(email)
                .filter(value -> value.equals(confirmationEmail))
                .orElseThrow(()-> new IllegalArgumentException("Emails do not match"));
    }

    public static UserDocument toEntity(RegisterUserDTO registerUserDTO){
        return UserDocument
                .builder()
                .firstName(registerUserDTO.getFirstName())
                .lastName(registerUserDTO.getLastName())
                .email(registerUserDTO.getEmail())
                .documentNumber(registerUserDTO.getDocumentNumber())
                .phoneNumber(registerUserDTO.getPhoneNumber())
                .registrationDate(LocalDateTime.now())
                .build();
    }
}
