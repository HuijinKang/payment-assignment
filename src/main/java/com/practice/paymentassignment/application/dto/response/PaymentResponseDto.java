package com.practice.paymentassignment.application.dto.response;

import com.practice.paymentassignment.domain.entity.SFTPay;

public record PaymentResponseDto(
        Long id,
        Long userId,
        Long amount,
        String storeName,
        boolean status
) {
    public static PaymentResponseDto of(SFTPay pay) {
        return new PaymentResponseDto(
                pay.getId(),
                pay.getUser().getId(),
                pay.getAmount(),
                pay.getStoreName(),
                pay.isStatus()
        );
    }
}
