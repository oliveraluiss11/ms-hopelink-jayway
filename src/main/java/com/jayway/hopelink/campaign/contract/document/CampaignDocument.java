package com.jayway.hopelink.campaign.contract.document;

import com.jayway.hopelink.campaign.enums.Category;
import com.jayway.hopelink.campaign.enums.RecipientType;
import lombok.*;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CampaignDocument {

    @Id
    private String campaignId;
    private Double targetAmount;
    private Integer postalCode;
    private Category category;
    private RecipientType recipientType;
    private String mediaUrl;
    private String title;
    private String description;

}