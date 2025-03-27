package com.practice.paymentassignment.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND("사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    USER_INFO_MISMATCH("사용자 정보가 일치하지 않습니다.", HttpStatus.NOT_FOUND),
    PAYMENT_INFO_NOT_FOUND("결제 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    INSUFFICIENT_BALANCE("잔액이 부족합니다.", HttpStatus.BAD_REQUEST),
    PAYMENT_ALREADY_COMPLETED("결제가 완료된 건입니다.", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}