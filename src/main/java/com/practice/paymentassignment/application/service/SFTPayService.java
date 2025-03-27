package com.practice.paymentassignment.application.service;

import com.practice.paymentassignment.application.dto.response.PaymentResponseDto;
import com.practice.paymentassignment.application.service.common.SFTPayReader;
import com.practice.paymentassignment.domain.entity.SFTPay;
import com.practice.paymentassignment.domain.entity.User;
import com.practice.paymentassignment.infrastructure.repository.SFTPayRepository;
import com.practice.paymentassignment.presentation.dto.request.ApprovePaymentRequestDto;
import com.practice.paymentassignment.presentation.dto.request.PaymentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SFTPayService {
    private final SFTPayRepository sftPayRepository;
    private final SFTPayReader sftPayReader;
    private final PGService pgService;


    // before
//    @Transactional
//    public PaymentResponseDto requestPayment(PaymentRequestDto request) {
//        User user = sftPayReader.findByUserId(request.userId());
//
//        SFTPay sftPay = SFTPay.create(user, request.amount(), request.storeName(),false);
//
//        SFTPay saved = sftPayRepository.save(sftPay);
//
//        return PaymentResponseDto.of(saved);
//    }

    // after
    @Transactional
    public SFTPay requestPayment(PaymentRequestDto request) {
        User user = sftPayReader.findByUserId(request.userId());

        SFTPay sftPay = SFTPay.create(user, request.amount(), request.storeName(),false);

        SFTPay saved = sftPayRepository.save(sftPay);

        return saved;
    }



    @Transactional
    public Boolean approvePayment(ApprovePaymentRequestDto request) {
        SFTPay sftPay = sftPayReader.findByPayId(request.id());
        sftPay.checkStatus();

        // PG API
        boolean result = pgService.payment(sftPay);

        sftPay.changeStatus(result);

        return true;
    }
}
