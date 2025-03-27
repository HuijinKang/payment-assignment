package com.practice.paymentassignment.infrastructure.repository;

import com.practice.paymentassignment.domain.entity.Account;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * 1. DB 수준에서 락이 걸림
     * 2. 해당 row 읽기, 쓰기 불가
     * 3. 락의 지속 범위는 트랜잭션이 끝날 때까지.
     * */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from Account a where a.id = :id")
    Optional<Account> findByIdForUpdate(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Account a SET a.balance = a.balance - :amount WHERE a.id = :id and a.balance >= :amount")
    int deductBalance(@Param("id") Long id, @Param("amount") Long amount);
}
