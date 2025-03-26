package com.practice.paymentassignment.presentation.controller;

import com.practice.paymentassignment.application.dto.response.PaymentResponseDto;
import com.practice.paymentassignment.application.service.SFTPayService;
import com.practice.paymentassignment.presentation.dto.request.ApprovePaymentRequestDto;
import com.practice.paymentassignment.presentation.dto.request.PaymentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/sftpays")
@RestController
@RequiredArgsConstructor
public class SFTPayController {
    private final SFTPayService sftPayService;

    @PostMapping("/requestPayment")
    public ResponseEntity<PaymentResponseDto> requestPayment(@RequestBody PaymentRequestDto request) {
        PaymentResponseDto response = sftPayService.requestPayment(request);

        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("/approvePayment")
    public ResponseEntity<Boolean> approvePayment(@RequestBody ApprovePaymentRequestDto request) {
        Boolean success = sftPayService.approvePayment(request);

        return ResponseEntity.status(200).body(success);
    }
}
