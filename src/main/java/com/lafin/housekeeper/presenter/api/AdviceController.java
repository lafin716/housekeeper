package com.lafin.housekeeper.presenter.api;

import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.ErrorResponse;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(value = InvalidInputException.class)
    public ResponseEntity<ResponseModel> invalidInput(InvalidInputException exception) {
        var body = new ErrorResponse(exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ResponseModel> except(Exception exception) {
        var body = new ErrorResponse(exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
