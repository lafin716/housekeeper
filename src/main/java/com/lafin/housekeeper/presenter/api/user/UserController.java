package com.lafin.housekeeper.presenter.api.user;

import com.lafin.housekeeper.presenter.api.user.request.CreateUserRequest;
import com.lafin.housekeeper.presenter.api.user.request.SignInRequest;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.ResponseModel;
import com.lafin.housekeeper.shared.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserAdapter userAdapter;

    @PostMapping("/create")
    public ResponseEntity<ResponseModel> create(@RequestBody CreateUserRequest request) throws InvalidInputException {
        return ResponseUtil.json(userAdapter.create(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<ResponseModel> signIn(@RequestBody SignInRequest request) throws InvalidInputException {
        return ResponseUtil.json(userAdapter.signIn(request));
    }
}
