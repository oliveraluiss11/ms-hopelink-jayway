package com.jayway.hopelink.campaign.contract.controller;

import com.jayway.hopelink.campaign.contract.service.CampaignService;
import com.jayway.hopelink.campaign.contract.service.RegisterCampaign;
import com.jayway.hopelink.campaign.dto.RegisterCampaignDTO;
import com.jayway.hopelink.common.dto.ErrorResponse;
import com.jayway.hopelink.user.exception.AlreadyUserExistsException;
import com.jayway.hopelink.user.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.jayway.hopelink.common.util.Utils.buildErrorResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    @PostMapping
    @Operation(summary = "Register a new campaign")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Campaign registered successfully"),
            @ApiResponse(responseCode = "409", description = "Campaign already exists",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> register(@RequestBody RegisterCampaignDTO request){
        campaignService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ExceptionHandler(AlreadyUserExistsException.class)
    public ResponseEntity<ErrorResponse> handleConflict(AlreadyUserExistsException ex) {
        ErrorResponse errorResponse = buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage(), ex.getLocalizedMessage());
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(IllegalArgumentException ex) {
        ErrorResponse errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getLocalizedMessage());
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(UserNotFoundException ex) {
        ErrorResponse errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), ex.getLocalizedMessage());
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

}
