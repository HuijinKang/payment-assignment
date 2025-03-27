package com.practice.paymentassignment.presentation.controller.resolver.query;

import com.practice.paymentassignment.domain.entity.User;
import com.practice.paymentassignment.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserQuery {

    private final UserRepository userRepository;

    @QueryMapping
    public User user(@Argument Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

}
