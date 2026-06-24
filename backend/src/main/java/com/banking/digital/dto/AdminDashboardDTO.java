package com.banking.digital.dto;
import com.banking.digital.dto.TransactionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminDashboardDTO {
    private long totalCustomers;
    private long totalLoans;
    private BigDecimal totalRevenue;
    private long fraudAlertsCount;
    private long newRegistrationsCount;
    private List<TransactionDTO> recentSystemTransactions;
}



