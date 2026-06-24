package com.banking.digital.service;
import com.banking.digital.dto.AccountDTO;
import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    AccountDTO createAccount(Long userId, String accountType);
    AccountDTO getAccountByNumber(String accountNumber);
    List<AccountDTO> getUserAccounts(Long userId);
    void updateBalance(String accountNumber, BigDecimal amount);
}


