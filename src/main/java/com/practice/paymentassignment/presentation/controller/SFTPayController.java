package com.practice.paymentassignment.presentation.controller;

import com.practice.paymentassignment.application.dto.response.PaymentResponseDto;
import com.practice.paymentassignment.application.service.SFTPayService;
import com.practice.paymentassignment.presentation.dto.request.PaymentRequestDto;
import com.practice.paymentassignment.presentation.dto.request.CreateSFTPayRequestDto;
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

    @PostMapping("/createPayment")
    public ResponseEntity<PaymentResponseDto> createPayment(@RequestBody CreateSFTPayRequestDto request) {
        PaymentResponseDto response = sftPayService.createPayment(request);

        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("/payment")
    public ResponseEntity<Boolean> payment(@RequestBody PaymentRequestDto request) {
        Boolean result = sftPayService.payment(request);

        return ResponseEntity.status(200).body(result);
    }
}
