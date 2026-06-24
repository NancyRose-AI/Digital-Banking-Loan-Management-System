package com.banking.digital.service;
import com.banking.digital.dto.TransactionRequest;
import com.banking.digital.dto.DepositRequest;
import com.banking.digital.dto.DepositVerifyRequest;
import java.util.Map;

public interface TransactionService {
    void transfer(TransactionRequest request);
    java.util.List<com.banking.digital.dto.TransactionDTO> getUserTransactions(Long userId);
    Map<String, Object> createDepositOrder(DepositRequest request);
    void verifyAndCompleteDeposit(DepositVerifyRequest request);
}




