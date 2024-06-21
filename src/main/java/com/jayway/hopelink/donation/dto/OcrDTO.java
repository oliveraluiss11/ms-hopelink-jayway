package com.jayway.hopelink.donation.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
public class OcrDTO {
    private String phone;
    private String destination;
    private String transactionDate;
    private LocalDateTime registrationDate;
    private String operationNumber;
    private BigDecimal amount;
    private String currencySymbol;
    private String source;
}
