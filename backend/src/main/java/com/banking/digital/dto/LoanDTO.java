package com.banking.digital.dto;
import com.banking.digital.entity.LoanStatus;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class LoanDTO {
    private Long id;
    private String loanReference;
    private BigDecimal principalAmount;
    private BigDecimal interestRate;
    private Integer tenureMonths;
    private LoanStatus status;
    private LocalDateTime createdAt;
    private List<EmiScheduleDTO> emiSchedules;
}


