package com.lafin.housekeeper.domain.user.interactor;

import com.lafin.housekeeper.domain.user.gateway.UserGateway;
import com.lafin.housekeeper.domain.user.interactor.UpdateUserInteractor;
import com.lafin.housekeeper.domain.user.model.User;
import com.lafin.housekeeper.domain.user.usecase.input.UpdateUserInput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@DisplayName("회원정보 수정 유스케이스 테스트")
class UpdateUserInteractorTest {

    @InjectMocks
    private UpdateUserInteractor updateUserInteractor;

    @Mock
    private UserGateway userGateway;

    @Test
    void 일반유저_정상수정() {
        var searchId = 1L;
        var modifiedNickName = "수정된재욱";
        var modifiedPassword = "modifiedPW";

        doReturn(expectNewUserModel()).when(userGateway).findById(searchId);
        doReturn(expectNewUserModel()).when(userGateway).save(any());

        var input = UpdateUserInput.builder()
                .id(searchId)
                .nickName(modifiedNickName)
                .password(modifiedPassword)
                .build();

        try {
            var result = updateUserInteractor.execute(input);
            System.out.println(result.getMessage());
            assertTrue(result.isResult());
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    void 일반유저_수정_ID_파라미터_누락() {
        var input = UpdateUserInput.builder()
                                                .build();
        assertThrows(InvalidInputException.class, () -> {
            try {
                updateUserInteractor.execute(input);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                throw e;
            }
        });
    }

    private User expectNewUserModel() {
        var user =  User.builder()
                .id(1L)
                .build();

        return user;
    }
}