package com.practice.paymentassignment.presentation.controller.resolver.mutation;

import com.practice.paymentassignment.application.service.AccountService;
import com.practice.paymentassignment.domain.entity.Account;
import com.practice.paymentassignment.presentation.dto.request.graphql.CreateAccountInput;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AccountMutation {
    private final AccountService accountService;

    // 통장 생성
    @MutationMapping
    public Account createAccount(@Argument("input") CreateAccountInput input) {
        return accountService.createAccount(input);
    }
}
