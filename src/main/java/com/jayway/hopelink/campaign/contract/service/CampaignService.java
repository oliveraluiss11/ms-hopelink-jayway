package com.jayway.hopelink.campaign.contract.service;

import com.jayway.hopelink.campaign.contract.repository.CampaignRepository;
import com.jayway.hopelink.campaign.dto.RegisterCampaignDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CampaignService implements RegisterCampaign {

    private final CampaignRepository campaignRepository;

    @Override
    public void register(RegisterCampaignDTO request) {
        request.ensureAttributes();
        campaignRepository.save(RegisterCampaignDTO.toEntity(request));
    }

}
