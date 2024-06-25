package com.jayway.hopelink.communication.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SendCommunication {
    private Boolean sendToCampaignPage;
    private Boolean sendToEmailDonor;
}
