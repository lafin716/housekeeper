package com.lafin.housekeeper.domain.auth.usecase.input;

import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("인증토큰 생성 입력값 테스트")
class CreateTokenInputTest {

    @Test
    void 입력값_정상() throws InvalidInputException {
        var input = CreateTokenInput.builder()
                .email("lafin716@gmail.com")
                .password("1234")
                .build();

        assertNotEquals(input.getEmail(), "");
        assertNotEquals(input.getPassword(), "");
        assertDoesNotThrow(input::validate);
        assertTrue(input.validate());
    }
}