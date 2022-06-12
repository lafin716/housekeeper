package com.lafin.housekeeper.domain.user.model;

import com.lafin.housekeeper.shared.contract.domain.model.Model;
import com.lafin.housekeeper.shared.status.UserStatus;
import com.lafin.housekeeper.shared.type.PlatformType;
import com.lafin.housekeeper.shared.type.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Builder
@ToString
public class User implements Model {

    private Long id;

    private UserType type;

    private PlatformType platform;

    private UserStatus status;

    private String email;

    private String nickName;

    private String password;

    private final int limitFailSignIn = 5;

    private final long recoveryFailSignInTime = 5;

    private int failSignInCount;

    private LocalDateTime overFailSignInAt;

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

    public void updated() {
        this.updatedAt = LocalDateTime.now();
    }

    public void banned() {
        this.status = UserStatus.BANNED;
        this.updatedAt = LocalDateTime.now();
    }

    public void tooManyFailedSignIn() {
        this.status = UserStatus.BANNED;
        this.updatedAt = LocalDateTime.now();
        this.overFailSignInAt = LocalDateTime.now();
    }

    public void recoveryFailSignIn() {
        this.failSignInCount = 0;
        this.overFailSignInAt = null;
        this.status = UserStatus.ALIVE;
    }

    public void updateInMyPage(String nickName, String password) {
        boolean isUpdated = updateNickname(nickName) && updatePassword(password);
        if (isUpdated) {
            updated();
        }
    }

    public void failedSignIn() {
        failedSignIn(limitFailSignIn);
    }

    public void failedSignIn(int limit) {
        this.failSignInCount++;

        if (this.failSignInCount >= limit) {
            tooManyFailedSignIn();
        }
    }

    public int getFailedSignIn() {
        if (isOverFailSignIn()) {
            return this.failSignInCount;
        }

        return 0;
    }

    public boolean isOverFailSignIn() {
        return isOverFailSignIn(limitFailSignIn, recoveryFailSignInTime);
    }

    public boolean isOverFailSignIn(int limit, long recoverTime) {
        if (Objects.nonNull(this.overFailSignInAt)) {
            if (this.overFailSignInAt.isBefore(LocalDateTime.now().minusMinutes(recoverTime))) {
                recoveryFailSignIn();
                return false;
            }

            return true;
        }
        if (this.failSignInCount >= limit) return true;
        if (this.status == UserStatus.BANNED) return true;

        return false;
    }

    private boolean updateNickname(String nickName) {
        if (Strings.isBlank(nickName)) return false;

        this.nickName = nickName;
        return true;
    }

    private boolean updatePassword(String password) {
        if (Strings.isBlank(password)) return false;

        this.password = password;
        return true;
    }
}
