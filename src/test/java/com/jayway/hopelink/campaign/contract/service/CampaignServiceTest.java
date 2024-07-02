package com.jayway.hopelink.campaign.contract.service;

import com.jayway.hopelink.campaign.contract.repository.CampaignRepository;
import com.jayway.hopelink.campaign.dto.RegisterCampaignDTO;
import com.jayway.hopelink.campaign.enums.Category;
import com.jayway.hopelink.campaign.enums.RecipientType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    void register_shouldCallRegisterMethod_whenCalledWithValidData() {
        // Arrange
        RegisterCampaignDTO request = new RegisterCampaignDTO(
                "1",
                100.00,
                51,
                Category.FAMILY,
                RecipientType.MYSELF,
                "https://example.com/media",
                "Title",
                "Description"
        );

        // Act
        campaignService.register(request);

        // Assert
        verify(campaignRepository, times(1)).save(RegisterCampaignDTO.toEntity(request));
    }

    @Test
    void register_shouldThrowIllegalArgumentException_whenCalledWithInvalidTargetAmount() {
        // Arrange
        RegisterCampaignDTO request = new RegisterCampaignDTO(
                "1",
                50.00,
                051,
                Category.FAMILY,
                RecipientType.MYSELF,
                "https://example.com/media",
                "Title",
                "Description"
        );

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> campaignService.register(request));
    }

}