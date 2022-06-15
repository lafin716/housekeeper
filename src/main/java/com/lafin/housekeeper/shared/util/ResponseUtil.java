package com.lafin.housekeeper.shared.util;

import com.lafin.housekeeper.shared.contract.presenter.viewmodel.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static ResponseEntity<ResponseModel> json(ResponseModel response) {
        if (response.isResult()) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
