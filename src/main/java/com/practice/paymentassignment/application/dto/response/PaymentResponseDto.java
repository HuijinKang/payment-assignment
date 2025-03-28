package com.practice.paymentassignment.application.dto.response;

import com.practice.paymentassignment.domain.entity.SFTPay;
import com.practice.paymentassignment.domain.entity.User;

public record PaymentResponseDto(
        Long id,
        User user,
        Long amount,
        String storeName,
        boolean status
) {
    public static PaymentResponseDto from(SFTPay pay) {
        return new PaymentResponseDto(
                pay.getId(),
                pay.getUser(),
                pay.getAmount(),
                pay.getStoreName(),
                pay.isStatus()
        );
    }
}
