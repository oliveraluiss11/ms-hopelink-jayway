package com.jayway.hopelink.campaign.dto;

import com.jayway.hopelink.campaign.contract.document.CampaignDocument;
import com.jayway.hopelink.campaign.enums.Category;
import com.jayway.hopelink.campaign.enums.RecipientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
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

    //    private String campaignId;
    private BigDecimal targetAmount;
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

    // mayor a 100
    private void ensureTargetAmountIsValid(BigDecimal value) {
        Optional.ofNullable(value)
                .filter(e -> e.compareTo(BigDecimal.valueOf(100)) > 0)
                .orElseThrow(() -> new IllegalArgumentException("El monto objetivo debe ser mayor a 100"));
    }

    // 5 dígitos
    private void ensurePostalCodeIsValid(Integer value) {
        if (value == null || !value.toString().matches("^\\d{5}$")) {
            throw new IllegalArgumentException("El codigo postal debe ser un numero de 5 digitos");
        }
    }

    private void ensureCategoryIsValid(Category value) {
        Optional.ofNullable(value)
                .filter(e -> Arrays.stream(Category.values()).anyMatch(e::equals))
                .orElseThrow(() -> new IllegalArgumentException("La categoría no es valida"));
    }

    private void ensureRecipientTypeIsValid(RecipientType value) {
        Optional.ofNullable(value)
                .filter(e -> Arrays.stream(RecipientType.values()).anyMatch(e::equals))
                .orElseThrow(() -> new IllegalArgumentException("El tipo de destinatario no es valido"));
    }

    private void ensureMediaUrlIsValid(String value) {
        Optional.ofNullable(value)
                .filter(e -> !e.isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("La URL del medio no puede estar vacia"));
        // Aquí agregar la validación para que mediaUrl sea una URL de Firebase Storage
    }

    private void ensureTitleIsValid(String value) {
        Optional.ofNullable(value)
                .filter(e -> !e.isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("El titulo no puede estar vacio"));
    }

    private void ensureDescriptionIsValid(String value) {
        Optional.ofNullable(value)
                .filter(e -> !e.isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("La descripción no puede estar vacía"));
    }

    public static CampaignDocument toEntity(RegisterCampaignDTO registerCampaignDTO) {
        return CampaignDocument
                .builder()
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
