package com.banking.digital.service;
import com.banking.digital.dto.CreditScoreResultDTO;

public interface CreditScoreService {
    CreditScoreResultDTO calculateAndSaveCreditScore(Long userId);
}


