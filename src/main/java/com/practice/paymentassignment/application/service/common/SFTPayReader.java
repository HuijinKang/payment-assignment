package com.practice.paymentassignment.application.service.common;

import com.practice.paymentassignment.common.CustomException;
import com.practice.paymentassignment.common.ErrorCode;
import com.practice.paymentassignment.domain.entity.Account;
import com.practice.paymentassignment.domain.entity.Bank;
import com.practice.paymentassignment.domain.entity.SFTPay;
import com.practice.paymentassignment.domain.entity.User;
import com.practice.paymentassignment.infrastructure.repository.AccountRepository;
import com.practice.paymentassignment.infrastructure.repository.BankRepository;
import com.practice.paymentassignment.infrastructure.repository.SFTPayRepository;
import com.practice.paymentassignment.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SFTPayReader {
    private final AccountRepository accountRepository;
    private final SFTPayRepository sftPayRepository;
    private final UserRepository userRepository;
    private final BankRepository bankRepository;

    @Transactional(readOnly = true)
    public Account findByAccountNumberForUpdate(String accountNumber) {
        return accountRepository.findByAccountNumberForUpdate(accountNumber)
                .orElseThrow(() -> new CustomException(ErrorCode.ACCOUNT_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public SFTPay findByPayId(Long id) {
        return sftPayRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.PAYMENT_INFO_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public User findByUserId(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Bank findByBankId(Long id) {
        return bankRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BANK_NOT_FOUND));
    }

    /**
     * Spring은 @Transactional을 AOP 프록시 객체로 감싸서 트랜잭션을 관리합니다.
     * 같은 클래스 내부에서 자기 메서드를 호출하면, Spring AOP 프록시가 가로채지 못해서 트랜잭션이 적용되지 않습니다.
     * 방법
     * 1. 자기 자신을 의존성으로 주입받아서 호출 (Self-injection)
     * 2. 트랜잭션 메서드를 다른 서비스로 분리 (더 깔끔한 구조)
     * */
}
