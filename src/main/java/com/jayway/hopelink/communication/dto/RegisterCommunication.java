package com.jayway.hopelink.communication.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterCommunication {
    private String documentNumber;
    private String message;
    private String mediaUrl;
    private SendCommunication sendCommunication;
}
