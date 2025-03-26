package com.practice.paymentassignment.presentation.dto.request;

public record PaymentRequestDto(
        Long userId,
        Long amount,
        String storeName
) {
}
