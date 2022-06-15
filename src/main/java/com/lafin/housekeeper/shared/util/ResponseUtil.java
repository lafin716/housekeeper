package com.lafin.housekeeper.shared.util;

import com.lafin.housekeeper.shared.contract.presenter.viewmodel.ResponseModel;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static ResponseEntity<ResponseModel> json(ResponseModel response) {

        return ResponseEntity.ok(response);
    }
}
