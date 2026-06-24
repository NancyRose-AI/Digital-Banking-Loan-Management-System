package com.banking.digital.dto;
import lombok.Data;

@Data
public class EmiPaymentVerifyRequest {
    private String razorpayPaymentId;
    private String razorpayOrderId;
    private String razorpaySignature;
}


