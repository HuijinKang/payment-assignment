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
public class PGService {
    private final BankService bankService;
    private final SFTPayReader sftPayReader;

    @Transactional
    public boolean payment(SFTPay request) {
        User user = sftPayReader
                .findByUserId(request.getUser().getId());
        user.matchesId(request.getUser().getId());

        bankService.requestPayment(request);

        return true;
    }
}
