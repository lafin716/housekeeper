package com.lafin.housekeeper.domain.auth.model;

import com.lafin.housekeeper.domain.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class Auth {

    private Long id;

    private User user;

    private String accessToken;

    private String refreshToken;

    private List<Scope> scopes;
}
