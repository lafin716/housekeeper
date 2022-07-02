package com.lafin.housekeeper.domain.user.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TokenInfo {

    private Long id;

    private String email;

    private LocalDateTime createdAt;

    public static TokenInfo create(Long id, String email) {
        return TokenInfo.builder()
                .id(id)
                .email(email)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
