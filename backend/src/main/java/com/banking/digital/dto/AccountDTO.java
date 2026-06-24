package com.banking.digital.dto;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class AccountDTO {
    private String accountNumber;
    private BigDecimal balance;
    private String accountType;
    private String status;
}


