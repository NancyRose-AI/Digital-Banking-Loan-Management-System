package com.banking.digital.service;
import com.banking.digital.dto.AuthRequest;
import com.banking.digital.dto.AuthResponse;
import com.banking.digital.dto.RegisterRequest;

public interface AuthService {
    AuthResponse login(AuthRequest request);
    AuthResponse register(RegisterRequest request);
    Long getUserIdByUsername(String username);
}


