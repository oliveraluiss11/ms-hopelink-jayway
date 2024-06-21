package com.jayway.hopelink.common.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ErrorResponse {
    private LocalDateTime registrationDate;
    private String path;
    private HttpStatus status;
    private String error;
    private String message;
}
