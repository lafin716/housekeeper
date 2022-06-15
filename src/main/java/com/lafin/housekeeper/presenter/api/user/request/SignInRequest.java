package com.lafin.housekeeper.presenter.api.user.request;

import com.lafin.housekeeper.shared.contract.presenter.viewmodel.RequestModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest implements RequestModel {

    private String email;

    private String password;
}
