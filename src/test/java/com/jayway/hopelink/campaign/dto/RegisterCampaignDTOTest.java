package com.jayway.hopelink.campaign.dto;

import com.jayway.hopelink.campaign.enums.Category;
import com.jayway.hopelink.campaign.enums.RecipientType;
import org.junit.jupiter.api.Test;

import static com.jayway.hopelink.campaign.enums.Category.ANIMALS;
import static com.jayway.hopelink.campaign.enums.RecipientType.MYSELF;
import static org.junit.jupiter.api.Assertions.*;

class RegisterCampaignDTOTest {

    @Test
    void testValidCampaign() {
        RegisterCampaignDTO campaign = new RegisterCampaignDTO(
                "Campaign",
                100.00,
                1000,
                ANIMALS,
                MYSELF,
                "https://www.google.com",
                "Titulo",
                "Descripcion de campaña");
        assertDoesNotThrow(campaign::ensureAttributes);
    }

    @Test
    void testInvalidTitle() {
        RegisterCampaignDTO campaign = new RegisterCampaignDTO(
                "Campaign",
                100.00,
                1000,
                ANIMALS,
                MYSELF,
                "https://www.google.com",
                "",
                "Descripcion de campaña");
        Exception exception = assertThrows(IllegalArgumentException.class, campaign::ensureAttributes);
        assertEquals("Title is not valid", exception.getMessage());
    }

    @Test
    void testInvalidDescription() {
        RegisterCampaignDTO campaign = new RegisterCampaignDTO(
                "Campaign",
                100.00,
                1000,
                ANIMALS,
                MYSELF,
                "https://www.google.com",
                "Titulo",
                "");
        Exception exception = assertThrows(IllegalArgumentException.class, campaign::ensureAttributes);
        assertEquals("Description is not valid", exception.getMessage());
    }

    @Test
    void testInvalidCategory() {
        RegisterCampaignDTO campaign = new RegisterCampaignDTO(
                "Campaign",
                100.00,
                1000,
                null,
                MYSELF,
                "https://www.google.com",
                "Titulo",
                "Descripcion de campaña");
        Exception exception = assertThrows(IllegalArgumentException.class, campaign::ensureAttributes);
        assertEquals("Category is not valid", exception.getMessage());
    }

    @Test
    void testInvalidRecipientType() {
        RegisterCampaignDTO campaign = new RegisterCampaignDTO(
                "Campaign",
                100.00,
                1000,
                ANIMALS,
                null,
                "https://www.google.com",
                "Titulo",
                "Descripcion de campaña");
        Exception exception = assertThrows(IllegalArgumentException.class, campaign::ensureAttributes);
        assertEquals("Recipient type is not valid", exception.getMessage());
    }

    @Test
    void testInvalidMediaUrl() {
        RegisterCampaignDTO campaign = new RegisterCampaignDTO(
                "Campaign",
                100.00,
                1000,
                ANIMALS,
                MYSELF,
                "",
                "Titulo",
                "Descripcion de campaña");
        Exception exception = assertThrows(IllegalArgumentException.class, campaign::ensureAttributes);
        assertEquals("Media URL is not valid", exception.getMessage());
    }

    @Test
    void testInvalidTargetAmount() {
        RegisterCampaignDTO campaign = new RegisterCampaignDTO(
                "Campaign",
                99.99,
                1000,
                ANIMALS,
                MYSELF,
                "https://www.google.com",
                "Titulo",
                "Descripcion de campaña");
        Exception exception = assertThrows(IllegalArgumentException.class, campaign::ensureAttributes);
        assertEquals("Target amount must be greater than or equal to 100 soles", exception.getMessage());
    }

}