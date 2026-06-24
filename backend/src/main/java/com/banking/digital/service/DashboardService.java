package com.banking.digital.service;
import com.banking.digital.dto.DashboardDTO;

public interface DashboardService {
    DashboardDTO getCustomerDashboard(Long userId);
    com.banking.digital.dto.AdminDashboardDTO getAdminDashboard();
    com.banking.digital.dto.EmployeeDashboardDTO getEmployeeDashboard();
}




