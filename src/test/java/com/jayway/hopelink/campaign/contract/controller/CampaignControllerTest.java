package com.jayway.hopelink.campaign.contract.controller;

import com.jayway.hopelink.campaign.contract.service.CampaignService;
import com.jayway.hopelink.campaign.dto.RegisterCampaignDTO;
import com.jayway.hopelink.campaign.enums.Category;
import com.jayway.hopelink.campaign.enums.RecipientType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CampaignControllerTest {

    @Mock
    private CampaignService campaignService;

    @InjectMocks
    private CampaignController campaignController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_shouldReturnCreatedStatus_whenCampaignRegisteredSuccessfully() {
        // Arrange
        RegisterCampaignDTO request = RegisterCampaignDTO.builder()
                .targetAmount(BigDecimal.valueOf(150.00))
                .postalCode(50000)
                .category(Category.FAMILY)
                .recipientType(RecipientType.MYSELF)
                .mediaUrl("https://example.com/media")
                .title("Title")
                .description("Description")
                .build();

        // Act
        ResponseEntity<Void> responseEntity = campaignController.register(request);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        verify(campaignService, times(1)).register(request);
    }

}