package com.lafin.housekeeper.domain.auth.model;

import com.lafin.housekeeper.domain.user.model.User;
import com.lafin.housekeeper.shared.contract.domain.model.Model;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class Auth implements Model {

    private Long id;

    private User user;

    private String accessToken;

    private String refreshToken;

    private List<Scope> scopes;
}
