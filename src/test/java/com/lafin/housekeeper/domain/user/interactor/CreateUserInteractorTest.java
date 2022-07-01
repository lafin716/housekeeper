package com.lafin.housekeeper.domain.user.interactor;

import com.lafin.housekeeper.domain.user.gateway.UserGateway;
import com.lafin.housekeeper.domain.user.model.User;
import com.lafin.housekeeper.domain.user.usecase.CreateUserUseCase;
import com.lafin.housekeeper.domain.user.usecase.input.CreateUserInput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.type.PlatformType;
import com.lafin.housekeeper.shared.type.UserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@DisplayName("회원가입 유스케이스 테스트")
class CreateUserInteractorTest {

    @InjectMocks
    private CreateUserUseCase createUserInteractor;

    @Mock
    private UserGateway userGateway;

    @Test
    void 일반유저_정상가입() {
        doReturn(expectNewUserModel()).when(userGateway).save(any());

        var input = CreateUserInput.builder()
                .email("lafin716@naver.com")
                .nickName("우기")
                .password("test")
                .type(UserType.USER)
                .platform(PlatformType.EMAIL)
                .build();

        try {
            var result = createUserInteractor.execute(input);
            System.out.println(result.getMessage());
            assertTrue(result.isResult());
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    void 중복회원가입_방지() {
        doReturn(expectNewUserModel()).when(userGateway).findByEmail(any());

        var input = CreateUserInput.builder()
                .email("lafin716@naver.com")
                .nickName("우기")
                .password("test")
                .type(UserType.USER)
                .platform(PlatformType.EMAIL)
                .build();
        try {
            var result = createUserInteractor.execute(input);
            System.out.println(result.getMessage());
            assertFalse(result.isResult());
            assertEquals(result.getMessage(), "이미 가입 된 이메일입니다.");
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
            "false,false,false,false,false",
            "true,false,false,false,false",
            "true,true,false,false,false",
            "true,true,true,false,false",
            "true,true,true,true,false"
    })
    void 일반유저_파라미터_누락(boolean email, boolean nickname, boolean password, boolean type, boolean platform) {
        var input = selectableUserInput(email, nickname, password, type, platform);
        assertThrows(InvalidInputException.class, () -> createUserInteractor.execute(input));
    }

    // 파라미터 존재 여부 선택 유저 등록 객체 생성
    private CreateUserInput selectableUserInput(boolean email, boolean nickname, boolean password, boolean type, boolean platform) {
        var builder = CreateUserInput.builder();

        if (email) builder.email("lafin716@gmail.com");
        if (nickname) builder.nickName("우기");
        if (password) builder.password("testpassword");
        if (type) builder.type(UserType.USER);
        if (platform) builder.platform(PlatformType.EMAIL);

        return builder.build();
    }

    private User expectNewUserModel() {
        var user =  User.builder()
                .id(1L)
                .email("lafin716@naver.com")
                .nickName("우기")
                .password("test")
                .type(UserType.USER)
                .platform(PlatformType.EMAIL)
                .build();
        user.created();

        return user;
    }
}