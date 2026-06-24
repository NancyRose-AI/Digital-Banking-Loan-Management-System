package com.banking.digital.service;
import com.banking.digital.dto.FinancialReportDTO;

public interface ReportService {
    FinancialReportDTO getReport(Long userId, String period);
}


