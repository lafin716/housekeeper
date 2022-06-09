package com.lafin.housekeeper.presenter.api;

import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.DefaultResponse;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.ResponseModel;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(value = InvalidInputException.class)
    public ResponseModel invalidInput(InvalidInputException exception) {
        return new DefaultResponse("rest");
    }
}
