package com.banking.digital.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDashboardDTO {
    private long pendingLoanApprovals;
    private long pendingKycVerifications;
    private long dailyOperationsCount;
    private long customerRequestsCount; // For demo purpose
}


