package com.jayway.hopelink.user.contract.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDocument {
    @Id
    private String userId;
    @Indexed
    private String documentNumber;
    @Indexed
    private String phoneNumber;
    private String firstName;
    private String lastName;
    @Indexed
    private String email;
    private LocalDateTime registrationDate;
    private LocalDateTime updatedDate;
}
