package com.banking.digital.service;
import com.banking.digital.dto.EmiScheduleDTO;
import com.banking.digital.dto.LoanDTO;
import com.banking.digital.dto.LoanRequest;
import java.util.List;

public interface LoanService {
    LoanDTO applyForLoan(Long userId, LoanRequest request);
    List<LoanDTO> getUserLoans(Long userId);
    LoanDTO approveLoan(Long loanId);
    java.util.Map<String, Object> createEmiPaymentOrder(Long loanId, int installmentNumber);
    void verifyAndPayEmi(Long loanId, int installmentNumber, com.banking.digital.dto.EmiPaymentVerifyRequest request);
    List<EmiScheduleDTO> getEmiSchedule(Long loanId);
}




