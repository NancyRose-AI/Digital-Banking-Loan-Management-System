package com.banking.digital.dto;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class DepositVerifyRequest {
    private Long userId;
    private String accountNumber;
    private BigDecimal amount;
    private String razorpayPaymentId;
    private String razorpayOrderId;
    private String razorpaySignature;
}


