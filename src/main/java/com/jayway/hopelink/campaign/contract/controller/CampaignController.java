package com.jayway.hopelink.campaign.contract.controller;

import com.jayway.hopelink.campaign.dto.RegisterCampaignDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {
    @PostMapping
    public ResponseEntity<Void> register(@RequestBody RegisterCampaignDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
