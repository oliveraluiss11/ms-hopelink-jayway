package com.jayway.hopelink.user.contract.controller;

import com.jayway.hopelink.common.dto.ErrorResponse;
import com.jayway.hopelink.user.contract.service.UserService;
import com.jayway.hopelink.user.dto.RegisterUserDTO;
import com.jayway.hopelink.user.dto.UserInfo;
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
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    @PostMapping
    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully"),
            @ApiResponse(responseCode = "409", description = "User already exists",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> register(@RequestBody RegisterUserDTO request){
        userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Operation(summary = "Find user by document number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User founded successfully"),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<UserInfo> findUserByDocumentNumber(@RequestParam String documentNumber){
        var userFounded = userService.findByDocumentNumber(documentNumber);
        return ResponseEntity.status(HttpStatus.OK).body(userFounded);
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
