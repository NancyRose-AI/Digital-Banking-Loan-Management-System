package com.banking.digital.dto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class LoanRequest {
    @NotNull(message = "Principal amount is required")
    @DecimalMin(value = "1000.00", message = "Minimum loan amount is 1000")
    private BigDecimal principalAmount;

    @NotNull(message = "Tenure is required")
    @Min(value = 6, message = "Minimum tenure is 6 months")
    private Integer tenureMonths;
}


