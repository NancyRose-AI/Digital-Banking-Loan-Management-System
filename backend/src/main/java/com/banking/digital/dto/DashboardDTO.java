package com.banking.digital.dto;
import com.banking.digital.dto.TransactionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {
    private BigDecimal totalBalance;
    private int activeLoans;
    private int recentTransactionsCount;
    private List<TransactionDTO> recentTransactions;
    private BigDecimal savingsBalance;
    private BigDecimal checkingBalance;
    private BigDecimal loanEmiBalance;
    private int creditScore;
    private String creditScoreRating;
    private List<String> creditScoreFactors;
    
    // For Asset Distribution Chart
    private BigDecimal activeLoanAmount;
    private BigDecimal totalPendingEmiAmount;
    private BigDecimal availableFunds;
    
    // Next pending EMI details — fetched live from emi_schedule table
    private BigDecimal upcomingEmiAmount;
    private LocalDate upcomingEmiDueDate;
    private Long upcomingEmiLoanId;
    private Integer upcomingEmiInstallmentNumber;
    private String kycStatus;
    
    // For Activity Bar Chart (Today vs Yesterday)
    private List<String> activityLabels;
    private List<Integer> activityTodayData;
    private List<Integer> activityYesterdayData;
}




