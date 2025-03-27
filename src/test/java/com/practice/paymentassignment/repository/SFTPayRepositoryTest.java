package com.practice.paymentassignment.repository;

import com.practice.paymentassignment.domain.entity.Account;
import com.practice.paymentassignment.infrastructure.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
@Rollback(false)
class SFTPayRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Test
    void 마지막_차감_금액_확인하기() throws InterruptedException {
        // given
        Account account = new Account(20000L);
        accountRepository.saveAndFlush(account);

        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        long startTime = System.currentTimeMillis(); // 시작 시간 측정

        for (int i = 0; i < threadCount; i++) {
            int threadNumber = i;
            executor.execute(() -> {
                TransactionStatus tx = transactionManager.getTransaction(new DefaultTransactionDefinition());
                try {
                    // 1. 락 없이 동시 접근
                    Account a = accountRepository.findById(account.getId()).orElseThrow();

                    // 2. 비관적 락 적용
//                    Account a = accountRepository.findByIdForUpdate(account.getId()).orElseThrow();

                    long deductAmount = 1000L + (threadNumber * 100); // 스레드마다 다른 금액
                    a.deductBalance(deductAmount);

                    // 3. DB에서 직접 계산
//                    long deductAmount = 1000L + (threadNumber * 100);
//                    int updated = accountRepository.deductBalance(account.getId(), deductAmount);
//                    if (updated == 0) {
//                        System.out.println("Thread " + threadNumber + " 실패 - 잔액 부족");
//                        transactionManager.rollback(tx);
//                        return;
//                    }

                    // 딜레이를 주어 충돌 유도
                    Thread.sleep(100);

                    transactionManager.commit(tx);
                    System.out.println("Thread " + threadNumber + " 차감 금액: " + deductAmount);
                } catch (Exception e) {
                    transactionManager.rollback(tx);
                    System.out.println("Thread " + threadNumber + " 실패");
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        long endTime = System.currentTimeMillis(); // 종료 시간 측정
        long duration = endTime - startTime;

        // then
        Account finalAccount = accountRepository.findById(account.getId()).orElseThrow();
        System.out.println("최종 잔액 = " + finalAccount.getBalance());

        long lastDeductedAmount = 20000L - finalAccount.getBalance();
        System.out.println("마지막에 반영된 차감 금액 = " + lastDeductedAmount);
        System.out.println("총 실행 시간(ms) = " + duration);
    }
}
