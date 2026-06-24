package com.banking.digital.service;
import com.banking.digital.entity.FraudLog;
import java.math.BigDecimal;

public interface FraudDetectionService {

    /** Check a transfer for fraud. Returns the created FraudLog if a rule fired, else null. */
    FraudLog checkTransfer(Long userId, BigDecimal amount);

    /** Check a deposit for fraud (unusual large deposit). */
    FraudLog checkDeposit(Long userId, BigDecimal amount);

    /** Check a loan application for abnormal amount. */
    FraudLog checkLoanApplication(Long userId, BigDecimal principalAmount);

    /** Check rapid transactions within the last 10 minutes. */
    FraudLog checkRapidTransactions(Long userId);

    /** Check for excessive EMI payment failures. */
    FraudLog checkEmiFailure(Long userId);

    /** Manually log a custom fraud event. */
    FraudLog logFraudEvent(Long userId, String eventType, String description, String riskLevel);
}


