package com.lafin.housekeeper.presenter.api.user.response;

import com.lafin.housekeeper.shared.contract.presenter.viewmodel.ResponseModel;
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

    private User user;

    @Getter
    @Builder
    @ToString
    public static class User {

        private String email;

        private String nickName;

        private String type;

        private String status;

        @DateTimeFormat(pattern = "YYYY-mm-dd HH:MM:ss")
        private LocalDateTime created;
    }
}
