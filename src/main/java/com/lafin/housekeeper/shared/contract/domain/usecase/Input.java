package com.lafin.housekeeper.shared.contract.domain.usecase;

public interface Input {

    boolean validate() throws InvalidInputException;
}
