package com.banking.digital.service.impl;
import com.banking.digital.entity.FraudLog;
import com.banking.digital.entity.User;
import com.banking.digital.repository.FraudLogRepository;
import com.banking.digital.repository.TransactionRepository;
import com.banking.digital.repository.UserRepository;
import com.banking.digital.service.FraudDetectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

@Slf4j
@Service
@RequiredArgsConstructor
public class FraudDetectionServiceImpl implements FraudDetectionService {

    // ── Thresholds ────────────────────────────────────────────────────────────
    private static final BigDecimal LARGE_TRANSFER_THRESHOLD   = new BigDecimal("100000");  // ₹1,00,000
    private static final BigDecimal LARGE_DEPOSIT_THRESHOLD    = new BigDecimal("500000");  // ₹5,00,000
    private static final BigDecimal ABNORMAL_LOAN_THRESHOLD    = new BigDecimal("1000000"); // ₹10,00,000
    private static final int        RAPID_TX_LIMIT             = 5;   // > 5 txns in 10 min
    private static final int        RAPID_TX_WINDOW_MINUTES    = 10;

    private final FraudLogRepository   fraudLogRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository       userRepository;

    // ── Rule 1: Large Transfer ────────────────────────────────────────────────
    @Override
    public FraudLog checkTransfer(Long userId, BigDecimal amount) {
        if (amount != null && amount.compareTo(LARGE_TRANSFER_THRESHOLD) > 0) {
            String msg = String.format(
                "Large transfer detected: ₹%s exceeds ₹%s threshold.",
                amount.toPlainString(), LARGE_TRANSFER_THRESHOLD.toPlainString()
            );
            log.warn("[FRAUD] userId={} – {}", userId, msg);
            return save(userId, "LARGE_TRANSFER", msg, "HIGH");
        }
        // Also check rapid transactions after every transfer
        return checkRapidTransactions(userId);
    }

    // ── Rule 2: Large Deposit ─────────────────────────────────────────────────
    @Override
    public FraudLog checkDeposit(Long userId, BigDecimal amount) {
        if (amount != null && amount.compareTo(LARGE_DEPOSIT_THRESHOLD) > 0) {
            String msg = String.format(
                "Unusually large deposit: ₹%s exceeds ₹%s threshold.",
                amount.toPlainString(), LARGE_DEPOSIT_THRESHOLD.toPlainString()
            );
            log.warn("[FRAUD] userId={} – {}", userId, msg);
            return save(userId, "SUSPICIOUS_DEPOSIT", msg, "MEDIUM");
        }
        return null;
    }

    // ── Rule 3: Abnormal Loan ─────────────────────────────────────────────────
    @Override
    public FraudLog checkLoanApplication(Long userId, BigDecimal principalAmount) {
        if (principalAmount != null && principalAmount.compareTo(ABNORMAL_LOAN_THRESHOLD) > 0) {
            String msg = String.format(
                "Abnormal loan request: ₹%s exceeds ₹%s limit.",
                principalAmount.toPlainString(), ABNORMAL_LOAN_THRESHOLD.toPlainString()
            );
            log.warn("[FRAUD] userId={} – {}", userId, msg);
            return save(userId, "ABNORMAL_LOAN", msg, "MEDIUM");
        }
        return null;
    }

    // ── Rule 4: Rapid Transactions ────────────────────────────────────────────
    @Override
    public FraudLog checkRapidTransactions(Long userId) {
        LocalDateTime window = LocalDateTime.now().minusMinutes(RAPID_TX_WINDOW_MINUTES);
        long count = transactionRepository.countRecentTransactionsByUser(userId, window);
        if (count > RAPID_TX_LIMIT) {
            // Avoid spamming: only log if we haven't flagged this in the last 10 min
            LocalDateTime recentSince = LocalDateTime.now().minusMinutes(RAPID_TX_WINDOW_MINUTES);
            boolean alreadyFlagged = !fraudLogRepository
                    .findRecentByUserAndType(userId, "RAPID_TRANSACTIONS", recentSince)
                    .isEmpty();
            if (!alreadyFlagged) {
                String msg = String.format(
                    "Rapid transactions: %d transactions initiated within %d minutes (limit: %d).",
                    count, RAPID_TX_WINDOW_MINUTES, RAPID_TX_LIMIT
                );
                log.warn("[FRAUD] userId={} – {}", userId, msg);
                return save(userId, "RAPID_TRANSACTIONS", msg, "MEDIUM");
            }
        }
        return null;
    }

    // ── Rule 5: EMI Payment Failures ──────────────────────────────────────────
    @Override
    public FraudLog checkEmiFailure(Long userId) {
        LocalDateTime window = LocalDateTime.now().minusDays(30);
        long count = fraudLogRepository.findRecentByUserAndType(userId, "EMI_PAYMENT_FAILURE", window).size();
        // Since we are logging each failure, if we hit 3 in 30 days, trigger excessive fraud alert
        if (count >= 3) {
            LocalDateTime recentSince = LocalDateTime.now().minusDays(1);
            boolean alreadyFlagged = !fraudLogRepository
                    .findRecentByUserAndType(userId, "EXCESSIVE_EMI_FAILURE", recentSince)
                    .isEmpty();
            if (!alreadyFlagged) {
                String msg = String.format("Excessive EMI failures: %d failed attempts in the last 30 days.", count);
                log.warn("[FRAUD] userId={} – {}", userId, msg);
                return save(userId, "EXCESSIVE_EMI_FAILURE", msg, "MEDIUM");
            }
        }
        return null;
    }

    // ── Manual / Custom Event ─────────────────────────────────────────
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public FraudLog logFraudEvent(Long userId, String eventType, String description, String riskLevel) {
        return save(userId, eventType, description, riskLevel);
    }

    // ── Internal save helper ──────────────────────────────────────────────────
    private FraudLog save(Long userId, String eventType, String description, String riskLevel) {
        User user = userRepository.findById(userId).orElse(null);
        FraudLog log = FraudLog.builder()
                .user(user)
                .eventType(eventType)
                .description(description)
                .riskLevel(riskLevel)
                .resolved(false)
                .build();
        return fraudLogRepository.save(log);
    }
}


