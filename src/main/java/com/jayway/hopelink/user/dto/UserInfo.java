package com.jayway.hopelink.user.dto;

import com.jayway.hopelink.user.contract.document.UserDocument;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@Builder
public class UserInfo {
    private String documentNumber;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime registrationDate;
    private LocalDateTime updatedDate;

    public static UserInfo from(UserDocument userDocument){
        return UserInfo
                .builder()
                .email(userDocument.getEmail())
                .phoneNumber(userDocument.getPhoneNumber())
                .documentNumber(userDocument.getDocumentNumber())
                .firstName(userDocument.getFirstName())
                .lastName(userDocument.getLastName())
                .registrationDate(userDocument.getRegistrationDate())
                .updatedDate(userDocument.getUpdatedDate())
                .build();
    }
}
