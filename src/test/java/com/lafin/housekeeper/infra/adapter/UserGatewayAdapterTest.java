package com.lafin.housekeeper.infra.adapter;

import com.lafin.housekeeper.domain.user.model.User;
import com.lafin.housekeeper.shared.status.UserStatus;
import com.lafin.housekeeper.shared.type.PlatformType;
import com.lafin.housekeeper.shared.type.UserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Base64Utils;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DisplayName("infra.user 유저 테스트")
class UserGatewayAdapterTest {

    @Autowired
    private UserGatewayAdapter adapter;

    @Test
    void 유저_등록_테스트() {

        var user = User.builder()
                .email("lafin716@gmail.com")
                .nickName("재욱")
                .password(Base64Utils.encodeToString("test".getBytes()))
                .platform(PlatformType.EMAIL)
                .type(UserType.USER)
                .status(UserStatus.ALIVE)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        var saved = adapter.save(user);
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertTrue(saved.getId() > 0);
    }

    @Test
    void 유저_아이디_조회() {}

    @Test
    void 유저_정보_업데이트() {}
}