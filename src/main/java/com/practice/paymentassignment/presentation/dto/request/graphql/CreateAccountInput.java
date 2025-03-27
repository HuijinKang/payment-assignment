package com.practice.paymentassignment.presentation.dto.request.graphql;

public record CreateAccountInput(
        Long userId,
        Long bankId,
        String accountNumber,
        Long balance
) {
}
