package com.practice.paymentassignment.infrastructure.repository;

import com.practice.paymentassignment.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
