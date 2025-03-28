package com.practice.paymentassignment.presentation.controller.resolver.query;

import com.practice.paymentassignment.application.service.common.SFTPayReader;
import com.practice.paymentassignment.domain.entity.User;
import com.practice.paymentassignment.presentation.dto.response.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserQuery {

    private final SFTPayReader reader;

    @QueryMapping
    public UserResponseDto user(@Argument Long id) {
        User user = reader.findByUserId(id);

        return UserResponseDto.from(user);
    }
}
