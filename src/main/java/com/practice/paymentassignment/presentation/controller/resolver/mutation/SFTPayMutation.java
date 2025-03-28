package com.practice.paymentassignment.presentation.controller.resolver.mutation;

import com.practice.paymentassignment.application.dto.response.PaymentResponseDto;
import com.practice.paymentassignment.application.service.SFTPayService;
import com.practice.paymentassignment.presentation.dto.request.CreateSFTPayRequestDto;
import com.practice.paymentassignment.presentation.dto.request.PaymentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SFTPayMutation {
    private final SFTPayService sftPayService;

    // 결제 정보 생성
    @MutationMapping
    public PaymentResponseDto createSFTPay(@Argument("request") CreateSFTPayRequestDto request) {
        return sftPayService.createPayment(request);
    }

    // 결제하기
    @MutationMapping
    public boolean payment(@Argument("request") PaymentRequestDto request) {
        return sftPayService.payment(request);
    }
}
