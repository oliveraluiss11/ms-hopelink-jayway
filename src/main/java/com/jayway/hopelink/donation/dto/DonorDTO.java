package com.jayway.hopelink.donation.dto;

import lombok.Getter;

@Getter
public class DonorDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String documentNumber;
    private String phoneNumber;
    private Boolean anonymous;
    private Boolean subscriptionConsent;
}
