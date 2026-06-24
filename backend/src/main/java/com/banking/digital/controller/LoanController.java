package com.banking.digital.controller;
import com.banking.digital.dto.ApiResponse;
import com.banking.digital.dto.EmiScheduleDTO;
import com.banking.digital.dto.LoanDTO;
import com.banking.digital.dto.LoanRequest;
import com.banking.digital.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.banking.digital.dto.EmiPaymentVerifyRequest;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    /**
     * Apply for a loan.
     * Frontend calls: POST /api/v1/loans/apply/{userId}
     */
    @PostMapping("/apply/{userId}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ResponseEntity<ApiResponse<LoanDTO>> applyForLoan(
            @PathVariable Long userId,
            @Valid @RequestBody LoanRequest request) {
        LoanDTO loan = loanService.applyForLoan(userId, request);
        return ResponseEntity.ok(ApiResponse.success("Loan application submitted successfully", loan));
    }

    /**
     * Get all loans for a specific user.
     * Frontend calls: GET /api/v1/loans/user/{userId}
     */
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ResponseEntity<ApiResponse<List<LoanDTO>>> getUserLoans(@PathVariable Long userId) {
        List<LoanDTO> loans = loanService.getUserLoans(userId);
        return ResponseEntity.ok(ApiResponse.success("Loans retrieved successfully", loans));
    }

    /**
     * Get EMI schedule for a specific loan.
     * Frontend calls: GET /api/v1/loans/{loanId}/emi
     */
    @GetMapping("/{loanId}/emi")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ResponseEntity<ApiResponse<List<EmiScheduleDTO>>> getEmiSchedule(@PathVariable Long loanId) {
        List<EmiScheduleDTO> schedule = loanService.getEmiSchedule(loanId);
        return ResponseEntity.ok(ApiResponse.success("EMI schedule retrieved", schedule));
    }

    /**
     * Admin approves a pending loan and generates EMI schedule.
     * PUT /api/v1/loans/{loanId}/approve
     */
    @PutMapping("/{loanId}/approve")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<ApiResponse<LoanDTO>> approveLoan(@PathVariable Long loanId) {
        LoanDTO loan = loanService.approveLoan(loanId);
        return ResponseEntity.ok(ApiResponse.success("Loan approved and amount credited to account", loan));
    }

    /**
     * Create Razorpay order for EMI payment.
     * POST /api/v1/loans/{loanId}/emi/{installmentNumber}/order
     */
    @PostMapping("/{loanId}/emi/{installmentNumber}/order")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ResponseEntity<ApiResponse<Map<String, Object>>> createEmiPaymentOrder(
            @PathVariable Long loanId,
            @PathVariable int installmentNumber) {
        Map<String, Object> orderDetails = loanService.createEmiPaymentOrder(loanId, installmentNumber);
        return ResponseEntity.ok(ApiResponse.success("Order created successfully", orderDetails));
    }

    /**
     * Verify Razorpay payment and mark EMI as paid.
     * PUT /api/v1/loans/{loanId}/emi/{installmentNumber}/pay
     */
    @PutMapping("/{loanId}/emi/{installmentNumber}/pay")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ResponseEntity<ApiResponse<String>> payEmi(
            @PathVariable Long loanId,
            @PathVariable int installmentNumber,
            @Valid @RequestBody EmiPaymentVerifyRequest request) {
        loanService.verifyAndPayEmi(loanId, installmentNumber, request);
        return ResponseEntity.ok(ApiResponse.success("EMI payment successful", "Installment " + installmentNumber + " paid"));
    }

    /**
     * Self-approve endpoint for testing (customer can approve own loan in dev mode).
     * PUT /api/v1/loans/{loanId}/self-approve
     */
    @PutMapping("/{loanId}/self-approve")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ResponseEntity<ApiResponse<LoanDTO>> selfApproveLoan(@PathVariable Long loanId) {
        LoanDTO loan = loanService.approveLoan(loanId);
        return ResponseEntity.ok(ApiResponse.success("Loan approved (self-approval for testing)", loan));
    }
}


