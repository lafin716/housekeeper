package com.lafin.housekeeper.domain.auth.usecase.input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("인증토큰 생성 입력값 테스트")
class CreateTokenInputTest {

    @Test
    void 입력값_정상() {
        var input = CreateTokenInput.builder().build();

        assertNotEquals(input.getEmail(), "");
        assertNotEquals(input.getPassword(), "");
    }
}