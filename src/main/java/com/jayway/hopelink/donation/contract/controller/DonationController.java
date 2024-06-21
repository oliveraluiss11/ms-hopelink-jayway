package com.jayway.hopelink.donation.contract.controller;

import com.jayway.hopelink.donation.dto.RegisterDonationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/donations")
public class DonationController {
    @PostMapping
    public ResponseEntity<Void> register(@RequestBody RegisterDonationDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
