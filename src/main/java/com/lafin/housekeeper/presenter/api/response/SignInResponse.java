package com.lafin.housekeeper.presenter.api.response;

import com.lafin.housekeeper.shared.contract.presenter.viewmodel.ResponseModel;
import com.lafin.housekeeper.shared.type.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class SignInResponse implements ResponseModel {

    private boolean result;

    private String message;

    private UserType type;

    private String accessToken;

    private String refreshToken;

    @DateTimeFormat(pattern = "YYYY-mm-dd HH:MM:ss")
    private LocalDateTime createdAt;
}
