package com.jayway.hopelink.campaign.dto;

import com.jayway.hopelink.campaign.contract.document.CampaignDocument;
import com.jayway.hopelink.campaign.enums.Category;
import com.jayway.hopelink.campaign.enums.RecipientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
@Getter
@Builder
public class RegisterCampaignDTO {
//    private String postalCode;
//    private String category;
//    private String recipientType;
//    private BigDecimal targetAmount;
//    private String mediaUrl;
//    private String title;
//    private String description;

    private String campaignId;
    private Double targetAmount;
    private Integer postalCode;
    private Category category;
    private RecipientType recipientType;
    private String mediaUrl;
    private String title;
    private String description;

    public void ensureAttributes() {
        ensureTargetAmountIsValid(this.targetAmount);
        ensurePostalCodeIsValid(this.postalCode);
        ensureCategoryIsValid(this.category);
        ensureRecipientTypeIsValid(this.recipientType);
        ensureMediaUrlIsValid(this.mediaUrl);
        ensureTitleIsValid(this.title);
        ensureDescriptionIsValid(this.description);
    }

    private void ensureTargetAmountIsValid(Double value) {
        Optional.ofNullable(value)
                .filter(e -> e >= 100)
                .orElseThrow(() -> new IllegalArgumentException("El monto objetivo debe ser mayor o igual a 100 soles"));
    }

    private void ensurePostalCodeIsValid(Integer value) {
        Optional.ofNullable(value)
                .filter(e -> e.toString().matches("\\d{5}"))
                .orElseThrow(() -> new IllegalArgumentException("El código postal debe ser un número de 5 dígitos"));
    }

    private void ensureCategoryIsValid(Category value) {
        Optional.ofNullable(value)
                .filter(e -> Arrays.stream(Category.values()).anyMatch(e::equals))
                .orElseThrow(() -> new IllegalArgumentException("La categoría no es válida"));
    }

    private void ensureRecipientTypeIsValid(RecipientType value) {
        Optional.ofNullable(value)
                .filter(e -> Arrays.stream(RecipientType.values()).anyMatch(e::equals))
                .orElseThrow(() -> new IllegalArgumentException("El tipo de destinatario no es válido"));
    }

    private void ensureMediaUrlIsValid(String value) {
        Optional.ofNullable(value)
                .filter(e -> !e.isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("La URL del medio no puede estar vacía"));
        // Aquí agregar la validación para que mediaUrl sea una URL de Firebase Storage
    }

    private void ensureTitleIsValid(String value) {
        Optional.ofNullable(value)
                .filter(e -> !e.isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("El título no puede estar vacío"));
    }

    private void ensureDescriptionIsValid(String value) {
        Optional.ofNullable(value)
                .filter(e -> !e.isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("La descripción no puede estar vacía"));
    }

    public static CampaignDocument toEntity(RegisterCampaignDTO registerCampaignDTO){
        return CampaignDocument
                .builder()
                .campaignId(registerCampaignDTO.getCampaignId())
                .targetAmount(registerCampaignDTO.getTargetAmount())
                .postalCode(registerCampaignDTO.getPostalCode())
                .category(registerCampaignDTO.getCategory())
                .recipientType(registerCampaignDTO.getRecipientType())
                .mediaUrl(registerCampaignDTO.getMediaUrl())
                .title(registerCampaignDTO.getTitle())
                .description(registerCampaignDTO.getDescription())
                .build();
    }

}
