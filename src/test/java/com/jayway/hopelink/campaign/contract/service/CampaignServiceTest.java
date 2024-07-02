package com.jayway.hopelink.campaign.contract.service;

import com.jayway.hopelink.campaign.contract.document.CampaignDocument;
import com.jayway.hopelink.campaign.contract.repository.CampaignRepository;
import com.jayway.hopelink.campaign.dto.RegisterCampaignDTO;
import com.jayway.hopelink.campaign.enums.Category;
import com.jayway.hopelink.campaign.enums.RecipientType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CampaignServiceTest {

    @Mock
    private CampaignRepository campaignRepository;

    @InjectMocks
    private CampaignService campaignService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerValidCampaign() {
        // Arrange
        RegisterCampaignDTO campaign = new RegisterCampaignDTO(
                BigDecimal.valueOf(150.00),
                50000,
                Category.FAMILY,
                RecipientType.MYSELF,
                "https://example.com/media",
                "Title",
                "Description"
        );

        campaignService.register(campaign);

        // Assert
        ArgumentCaptor<CampaignDocument> campaignCaptor = ArgumentCaptor.forClass(CampaignDocument.class);
        verify(campaignRepository, times(1)).save(campaignCaptor.capture());
        CampaignDocument savedCampaign = campaignCaptor.getValue();

        assertNotNull(savedCampaign);
        assertEquals(campaign.getTargetAmount(), savedCampaign.getTargetAmount());
        assertEquals(campaign.getPostalCode(), savedCampaign.getPostalCode());
        assertEquals(campaign.getCategory(), savedCampaign.getCategory());
        assertEquals(campaign.getRecipientType(), savedCampaign.getRecipientType());
        assertEquals(campaign.getMediaUrl(), savedCampaign.getMediaUrl());
        assertEquals(campaign.getTitle(), savedCampaign.getTitle());
        assertEquals(campaign.getDescription(), savedCampaign.getDescription());

    }

    @Test
    void registerInvalidCampaign() {
        // Arrange
        RegisterCampaignDTO campaign = new RegisterCampaignDTO(
                BigDecimal.valueOf(50.00),
                50000,
                Category.FAMILY,
                RecipientType.MYSELF,
                "https://example.com/media",
                "Title",
                "Description"
        );

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> campaignService.register(campaign));

        // Assert
        assertEquals("El monto objetivo debe ser mayor a 100", exception.getMessage());
    }

}