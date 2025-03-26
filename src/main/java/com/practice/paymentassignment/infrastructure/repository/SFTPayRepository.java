package com.practice.paymentassignment.infrastructure.repository;

import com.practice.paymentassignment.domain.entity.SFTPay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SFTPayRepository extends JpaRepository<SFTPay, Long> {
}
