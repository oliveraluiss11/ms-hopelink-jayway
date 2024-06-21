package com.jayway.hopelink.donation.dto;

import lombok.Getter;

import java.math.BigDecimal;
@Getter
public class RegisterDonationDTO {

    private BigDecimal amountDonation;
    private BigDecimal contributionPercentage;
    private String campaignId;
    private String operationToken;
    private DonorDTO donor;
}
