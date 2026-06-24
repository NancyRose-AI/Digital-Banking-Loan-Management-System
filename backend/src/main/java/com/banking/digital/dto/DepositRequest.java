package com.banking.digital.dto;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class DepositRequest {
    private Long userId;
    private String accountNumber;
    private BigDecimal amount;
}


