package com.practice.paymentassignment.presentation.dto.response;

import com.practice.paymentassignment.domain.entity.Account;
import com.practice.paymentassignment.domain.entity.User;

import java.util.List;

public record UserResponseDto(
        Long id,
        String name,
        List<Account> accounts
) {
    public static UserResponseDto from(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getAccounts()
        );
    }
}
