package com.jayway.hopelink.campaign.dto;

import com.jayway.hopelink.campaign.enums.Category;
import com.jayway.hopelink.campaign.enums.RecipientType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class RegisterCampaignDTOTest {

    @Test
    void testValidCampaign() {
        RegisterCampaignDTO campaign = new RegisterCampaignDTO(
                BigDecimal.valueOf(150.00),
                50000,
                Category.FAMILY,
                RecipientType.MYSELF,
                "https://example.com/media",
                "Title",
                "Description"
        );
        assertDoesNotThrow(campaign::ensureAttributes);
    }

    @Test
    void testInvalidTargetAmount() {
        RegisterCampaignDTO campaign = new RegisterCampaignDTO(
                BigDecimal.valueOf(50.00),
                50000,
                Category.FAMILY,
                RecipientType.MYSELF,
                "https://example.com/media",
                "Title",
                "Description"
        );
        Exception exception = assertThrows(IllegalArgumentException.class, campaign::ensureAttributes);
        assertEquals("El monto objetivo debe ser mayor a 100", exception.getMessage());
    }

    @Test
    void testInvalidPostalCode() {
        RegisterCampaignDTO campaign = new RegisterCampaignDTO(
                BigDecimal.valueOf(150.00),
                3333,
                Category.FAMILY,
                RecipientType.MYSELF,
                "https://example.com/media",
                "Title",
                "Description"
        );
        Exception exception = assertThrows(IllegalArgumentException.class, campaign::ensureAttributes);
        assertEquals("El codigo postal debe ser un numero de 5 digitos", exception.getMessage());
    }

    @Test
    void testInvalidCategory() {
        RegisterCampaignDTO campaign = new RegisterCampaignDTO(
                BigDecimal.valueOf(150.00),
                50000,
                null,
                RecipientType.MYSELF,
                "https://example.com/media",
                "Title",
                "Description"
        );
        Exception exception = assertThrows(IllegalArgumentException.class, campaign::ensureAttributes);
        assertEquals("La categoría no es valida", exception.getMessage());
    }

    @Test
    void testInvalidRecipientType() {
        RegisterCampaignDTO campaign = new RegisterCampaignDTO(
                BigDecimal.valueOf(150.00),
                50000,
                Category.FAMILY,
                null,
                "https://example.com/media",
                "Title",
                "Description"
        );
        Exception exception = assertThrows(IllegalArgumentException.class, campaign::ensureAttributes);
        assertEquals("El tipo de destinatario no es valido", exception.getMessage());
    }

    @Test
    void testInvalidMediaUrl() {
        RegisterCampaignDTO campaign = new RegisterCampaignDTO(
                BigDecimal.valueOf(150.00),
                50000,
                Category.FAMILY,
                RecipientType.MYSELF,
                null,
                "Title",
                "Description"
        );
        Exception exception = assertThrows(IllegalArgumentException.class, campaign::ensureAttributes);
        assertEquals("La URL del medio no puede estar vacia", exception.getMessage());
    }

    @Test
    void testInvalidTitle() {
        RegisterCampaignDTO campaign = new RegisterCampaignDTO(
                BigDecimal.valueOf(150.00),
                50000,
                Category.FAMILY,
                RecipientType.MYSELF,
                "https://example.com/media",
                null,
                "Description"
        );
        Exception exception = assertThrows(IllegalArgumentException.class, campaign::ensureAttributes);
        assertEquals("El titulo no puede estar vacio", exception.getMessage());
    }

    @Test
    void testInvalidDescription() {
        RegisterCampaignDTO campaign = new RegisterCampaignDTO(
                BigDecimal.valueOf(150.00),
                50000,
                Category.FAMILY,
                RecipientType.MYSELF,
                "https://example.com/media",
                "Title",
                null
        );
        Exception exception = assertThrows(IllegalArgumentException.class, campaign::ensureAttributes);
        assertEquals("La descripción no puede estar vacía", exception.getMessage());
    }




}