package com.lafin.housekeeper.domain.user.model;

import com.lafin.housekeeper.shared.contract.domain.model.Model;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@ToString
public class Auth implements Model {

    private Long id;

    private String accessToken;

    private String refreshToken;

    private List<Scope> scopes;

    private LocalDateTime createdAt;
}
