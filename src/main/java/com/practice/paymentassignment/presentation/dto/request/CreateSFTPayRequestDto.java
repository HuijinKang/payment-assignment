package com.practice.paymentassignment.presentation.dto.request;

public record CreateSFTPayRequestDto(
        Long userId,
        Long amount,
        String storeName
) {
}
