package com.banking.digital.dto;
import com.banking.digital.dto.TransactionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinancialReportDTO {

    // Filter period used to generate this report
    private String period; // TODAY, THIS_MONTH, ALL_TIME

    // Core Metrics
    private BigDecimal totalDeposits;
    private BigDecimal totalWithdrawals;
    private BigDecimal totalTransfers;
    private BigDecimal totalLoanAmount;
    private BigDecimal totalEmiPaid;
    private BigDecimal currentBalance;
    private int creditScore;
    private String creditScoreRating;

    // Transaction counts
    private int depositCount;
    private int withdrawalCount;
    private int transferCount;
    private int emiPaymentCount;
    private int loanCount;

    // Monthly summary: key = "YYYY-MM", value = total transaction amount
    private Map<String, BigDecimal> monthlySummary;

    // Monthly labels & values for chart (derived from monthlySummary)
    private List<String> monthlyLabels;
    private List<BigDecimal> monthlyDepositAmounts;
    private List<BigDecimal> monthlyWithdrawalAmounts;
    private List<BigDecimal> monthlyTransferAmounts;

    // All transactions for the table
    private List<TransactionDTO> transactions;
}



