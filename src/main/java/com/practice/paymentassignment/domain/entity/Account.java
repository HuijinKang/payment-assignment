package com.practice.paymentassignment.domain.entity;

import com.practice.paymentassignment.domain.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String accountNumber;

    @Column
    private Long balance;
    // 중간 테이블로 다대다 관계 해소
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    public Account(long balance) {
        this.balance = balance;
    }

    public void checkAccount() {
        if (this.balance < 0) {
            throw new RuntimeException("잔액 부족");
        }
    }

    public void deductBalance(Long amount) {
        if (this.balance < amount) {
            throw new RuntimeException("잔액 부족");
        }
        this.balance -= amount;
    }
}
