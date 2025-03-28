package com.practice.paymentassignment.presentation.dto.request;

public record PaymentRequestDto(
        Long sftPayId,
        String accountNumber
) {
}
