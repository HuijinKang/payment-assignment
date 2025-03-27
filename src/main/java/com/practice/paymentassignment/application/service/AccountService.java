package com.practice.paymentassignment.application.service;

import com.practice.paymentassignment.application.service.common.SFTPayReader;
import com.practice.paymentassignment.common.CustomException;
import com.practice.paymentassignment.common.ErrorCode;
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
    private final SFTPayReader sftPayReader;

    public Account createAccount(CreateAccountInput input) {
        User user = sftPayReader.findByUserId(input.userId());
        Bank bank = sftPayReader.findByBankId(input.bankId());

        Account account = Account.create(
                user,
                bank,
                input.accountNumber(),
                input.balance()
                );

        return accountRepository.save(account);
    }
}
