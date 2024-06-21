package com.jayway.hopelink.campaign.dto;

import lombok.Getter;

import java.math.BigDecimal;
@Getter
public class RegisterCampaignDTO {
    private String postalCode;
    private String category;
    private String recipientType;
    private BigDecimal targetAmount;
    private String mediaUrl;
    private String title;
    private String description;
}
