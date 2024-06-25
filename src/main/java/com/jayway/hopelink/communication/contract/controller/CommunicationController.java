package com.jayway.hopelink.communication.contract.controller;

import com.jayway.hopelink.communication.dto.RegisterCommunication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/communications")
public class CommunicationController {

    @PostMapping
    public ResponseEntity<Void> registerSupportMessage(@RequestBody RegisterCommunication request){
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
