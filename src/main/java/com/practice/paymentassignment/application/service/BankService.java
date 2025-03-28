package com.practice.paymentassignment.application.service;

import com.practice.paymentassignment.application.service.common.SFTPayReader;
import com.practice.paymentassignment.domain.entity.Account;
import com.practice.paymentassignment.domain.entity.SFTPay;
import com.practice.paymentassignment.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BankService {
    private final SFTPayReader sftPayReader;

    //
    @Transactional
    public void requestPayment(String accountNumber, Long amount) {
        Account account = sftPayReader
                .findByAccountNumberForUpdate(accountNumber);

        account.checkAccount();
        account.deductBalance(amount);
    }
}
