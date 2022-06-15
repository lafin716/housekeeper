package com.lafin.housekeeper.application.user.interactor;

import com.lafin.housekeeper.domain.user.gateway.UserGateway;
import com.lafin.housekeeper.domain.user.interactor.SignInUserInteractor;
import com.lafin.housekeeper.domain.user.model.User;
import com.lafin.housekeeper.domain.user.usecase.input.SignInInput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.status.UserStatus;
import com.lafin.housekeeper.shared.type.PlatformType;
import com.lafin.housekeeper.shared.type.UserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Base64Utils;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(value = MockitoExtension.class)
@DisplayName("회원 로그인 유스케이스 테스트")
class SignInUserInteractorTest {

    private String email = "lafin716@gmail.com";

    @InjectMocks
    private SignInUserInteractor signInUserInteractor;

    @Mock
    private UserGateway userGateway;

    @Test
    void 정상_로그인() throws InvalidInputException {
        doReturn(getDefaultUser()).when(userGateway).findByEmail(email);
        doReturn(getDefaultUser()).when(userGateway).save(any());

        var input = SignInInput.builder()
                .email("lafin716@gmail.com")
                .password("test")
                .build();
        var result = signInUserInteractor.execute(input);
        System.out.println(result.getMessage());
        System.out.println(result.getUser());

        assertTrue(result.isResult());
        assertEquals(result.getMessage(), "로그인 완료");
        assertNotNull(result.getUser());
    }

    @Test
    void 비밀번호_실패() throws InvalidInputException {
        doReturn(getDefaultUser()).when(userGateway).findByEmail(email);
        doReturn(getDefaultUser()).when(userGateway).save(any());

        var input = SignInInput.builder()
                .email("lafin716@gmail.com")
                .password("test123")
                .build();
        var result = signInUserInteractor.execute(input);
        System.out.println(result.getMessage());

        assertFalse(result.isResult());
        assertEquals(result.getMessage(), "회원정보가 일치하지 않습니다. 다시 한번 확인해주세요.");
    }

    @Test
    void 비밀번호_실패_초과() throws InvalidInputException {
        doReturn(getOverFailedUser()).when(userGateway).findByEmail(email);
        doReturn(getOverFailedUser()).when(userGateway).save(any());

        var input = SignInInput.builder()
                .email("lafin716@gmail.com")
                .password("test")
                .build();
        var result = signInUserInteractor.execute(input);
        System.out.println(result.getMessage());

        assertFalse(result.isResult());
    }

    @Test
    void 비밀번호_실패_복구() throws InvalidInputException {
        doReturn(getRecoveredUser()).when(userGateway).findByEmail(email);
        doReturn(getRecoveredUser()).when(userGateway).save(any());

        var input = SignInInput.builder()
                .email("lafin716@gmail.com")
                .password("test")
                .build();
        var result = signInUserInteractor.execute(input);
        System.out.println(result.getMessage());
        System.out.println(result.getUser());

        assertTrue(result.isResult());
        assertNotNull(result.getUser());
    }

    private User getOverFailedUser() {
        return User.builder()
                .id(1L)
                .email(email)
                .nickName("박재욱")
                .password(Base64Utils.encodeToString("test".getBytes()))
                .type(UserType.USER)
                .platform(PlatformType.EMAIL)
                .status(UserStatus.BANNED)
                .failSignInCount(5)
                .overFailSignInAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    private User getRecoveredUser() {
        return User.builder()
                .id(1L)
                .email(email)
                .nickName("박재욱")
                .password(Base64Utils.encodeToString("test".getBytes()))
                .type(UserType.USER)
                .platform(PlatformType.EMAIL)
                .status(UserStatus.BANNED)
                .failSignInCount(5)
                .overFailSignInAt(LocalDateTime.now().minusMinutes(6L))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    private User getDefaultUser() {
        return User.builder()
                .id(1L)
                .email(email)
                .nickName("박재욱")
                .password(Base64Utils.encodeToString("test".getBytes()))
                .type(UserType.USER)
                .platform(PlatformType.EMAIL)
                .status(UserStatus.ALIVE)
                .failSignInCount(0)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}