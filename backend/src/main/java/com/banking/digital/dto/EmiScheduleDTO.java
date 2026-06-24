package com.banking.digital.dto;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class EmiScheduleDTO {
    private Long id;
    private Integer installmentNumber;
    private BigDecimal emiAmount;
    private BigDecimal principalComponent;
    private BigDecimal interestComponent;
    private BigDecimal outstandingBalance;
    private LocalDate dueDate;
    private String status;
}


