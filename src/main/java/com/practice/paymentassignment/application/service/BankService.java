package com.practice.paymentassignment.application.service;

import com.practice.paymentassignment.application.service.common.SFTPayReader;
import com.practice.paymentassignment.domain.entity.Account;
import com.practice.paymentassignment.domain.entity.SFTPay;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankService {
    private final SFTPayReader sftPayReader;

    public void requestPayment(SFTPay request) {
        Account account = sftPayReader
                .findByIdForUpdate(request.getUser().getId());

        account.checkAccount();
        account.deductBalance(request.getAmount());
    }
}
