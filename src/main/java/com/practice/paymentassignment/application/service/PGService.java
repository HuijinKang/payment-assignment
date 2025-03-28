package com.practice.paymentassignment.application.service;

import com.practice.paymentassignment.application.service.common.SFTPayReader;
import com.practice.paymentassignment.domain.entity.SFTPay;
import com.practice.paymentassignment.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PGService {
    private final BankService bankService;
    private final SFTPayReader sftPayReader;

    @Transactional
    public boolean payment(SFTPay request, String accountNumber) {
        Long amount = request.getAmount();
        Long userId = request.getUser().getId();

        User user = sftPayReader
                .findByUserId(userId);

        user.matchesId(userId);

        bankService.requestPayment(accountNumber, amount);

        return true;
    }
}
