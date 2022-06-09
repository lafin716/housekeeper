package com.lafin.housekeeper.domain.user.model;

import com.lafin.housekeeper.shared.contract.domain.model.Model;
import com.lafin.housekeeper.shared.status.UserStatus;
import com.lafin.housekeeper.shared.type.PlatformType;
import com.lafin.housekeeper.shared.type.UserType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class User implements Model {

    private Long id;

    private UserType type;

    private PlatformType platform;

    private UserStatus status;

    private String email;

    private String nickName;

    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public void onReviewed() {
        this.status = UserStatus.ON_REVIEW;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void created() {
        this.status = UserStatus.ALIVE;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void banned() {
        this.status = UserStatus.BANNED;
        this.updatedAt = LocalDateTime.now();
    }
}
