package com.practice.paymentassignment.application.service;

import com.practice.paymentassignment.domain.entity.Account;
import com.practice.paymentassignment.domain.entity.Bank;
import com.practice.paymentassignment.domain.entity.User;
import com.practice.paymentassignment.infrastructure.repository.AccountRepository;
import com.practice.paymentassignment.infrastructure.repository.BankRepository;
import com.practice.paymentassignment.infrastructure.repository.UserRepository;
import com.practice.paymentassignment.presentation.dto.request.graphql.CreateAccountInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final UserRepository userRepository;
    private final BankRepository bankRepository;
    private final AccountRepository accountRepository;

    public Account createAccount(CreateAccountInput input) {
        User user = userRepository.findById(input.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Bank bank = bankRepository.findById(input.bankId())
                .orElseThrow(() -> new RuntimeException("Bank not found"));


        Account account = Account.create(
                user,
                bank,
                input.accountNumber(),
                input.balance()
                );

        return accountRepository.save(account);
    }
}
