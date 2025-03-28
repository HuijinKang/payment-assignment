package com.practice.paymentassignment.application.service;

import com.practice.paymentassignment.application.dto.response.PaymentResponseDto;
import com.practice.paymentassignment.application.service.common.SFTPayReader;
import com.practice.paymentassignment.domain.entity.SFTPay;
import com.practice.paymentassignment.domain.entity.User;
import com.practice.paymentassignment.infrastructure.repository.SFTPayRepository;
import com.practice.paymentassignment.presentation.dto.request.PaymentRequestDto;
import com.practice.paymentassignment.presentation.dto.request.CreateSFTPayRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SFTPayService {
    private final SFTPayRepository sftPayRepository;
    private final SFTPayReader sftPayReader;
    private final PGService pgService;

    @Transactional
    public PaymentResponseDto createPayment(CreateSFTPayRequestDto request) {
        User user = sftPayReader.findByUserId(request.userId());

        SFTPay sftPay = SFTPay.create(user, request.amount(), request.storeName(),false);

        SFTPay saved = sftPayRepository.save(sftPay);

        return PaymentResponseDto.from(saved);
    }

    @Transactional
    public Boolean payment(PaymentRequestDto request) {
        SFTPay sftPay = sftPayReader.findByPayId(request.sftPayId());
        sftPay.checkStatus();
        String accountNumber = request.accountNumber();

        // PG API
        boolean result = pgService.payment(sftPay, accountNumber);

        sftPay.changeStatus(result);

        return true;
    }
}
