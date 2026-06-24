package com.banking.digital.dto;
import com.banking.digital.dto.TransactionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FraudAlertDTO {
    private Long id;
    private Long userId;
    private String username;
    private String eventType;
    private String description;
    private String riskLevel;    // LOW | MEDIUM | HIGH
    private Boolean resolved;
    private LocalDateTime createdAt;
}



