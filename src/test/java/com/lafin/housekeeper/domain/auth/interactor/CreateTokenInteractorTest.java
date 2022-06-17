package com.lafin.housekeeper.domain.auth.interactor;

import com.lafin.housekeeper.domain.user.gateway.AuthGateway;
import com.lafin.housekeeper.domain.user.interactor.CreateTokenInteractor;
import com.lafin.housekeeper.domain.user.usecase.input.CreateTokenInput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@DisplayName("인증 토큰 생성 유스케이스 테스트")
class CreateTokenInteractorTest {

    @InjectMocks
    private CreateTokenInteractor createTokenInteractor;

    @Mock
    private AuthGateway authRepository;

    @Test
    void 인증토큰_생성() throws InvalidInputException {

        var input = CreateTokenInput.builder()
                .email("lafin716@gmail.com")
                .password("1234")
                .build();

        var result = createTokenInteractor.execute(input);

        assertNotNull(result);
        assertTrue(result.isResult());
        assertTrue(Strings.isNotBlank(result.getAccessToken()));
        assertTrue(Strings.isNotBlank(result.getRefreshToken()));
    }
}