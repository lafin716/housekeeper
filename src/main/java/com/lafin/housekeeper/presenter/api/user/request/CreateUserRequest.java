package com.lafin.housekeeper.presenter.api.user.request;

import com.lafin.housekeeper.shared.contract.presenter.viewmodel.RequestModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest implements RequestModel {

    private String type;

    private String platform;

    private String email;

    private String nickName;

    private String password;
}
