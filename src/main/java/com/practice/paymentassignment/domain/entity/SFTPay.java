package com.practice.paymentassignment.domain.entity;

import com.practice.paymentassignment.common.CustomException;
import com.practice.paymentassignment.common.ErrorCode;
import com.practice.paymentassignment.domain.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SFTPay extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column
    private Long amount;

    @Column
    private String storeName;

    @Column
    private boolean status;

    public SFTPay(User user, Long amount, String storeName, boolean status) {
        this.user = user;
        this.amount = amount;
        this.storeName = storeName;
        this.status = status;
    }

    public static SFTPay create(
            User user,
            Long amount,
            String storeName,
            boolean status
    ) {
        return new SFTPay(
                user,
                amount,
                storeName,
                status
        );
    }
    //JPA의 변경 감지(dirty checking)
    public void checkStatus() {
        if (this.status) {
            throw new CustomException(ErrorCode.PAYMENT_ALREADY_COMPLETED);
        }
    }

    public void changeStatus(boolean status) {
        if (this.status) {
            throw new CustomException(ErrorCode.PAYMENT_ALREADY_COMPLETED);
        }

        this.status = status;
    }
}
