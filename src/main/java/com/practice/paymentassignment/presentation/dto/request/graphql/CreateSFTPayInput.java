package com.practice.paymentassignment.presentation.dto.request.graphql;

import com.practice.paymentassignment.presentation.dto.request.PaymentRequestDto;

public record CreateSFTPayInput(
        Long userId,
        Long amount,
        String storeName
) {
    public PaymentRequestDto toPaymentRequest() {
        return new PaymentRequestDto(userId, amount, storeName);
    }
}

