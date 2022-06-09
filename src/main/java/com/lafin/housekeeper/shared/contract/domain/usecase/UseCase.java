package com.lafin.housekeeper.shared.contract.domain.usecase;

public interface UseCase<I extends Input, O extends Output> {

    default O execute() {
        return null;
    }

    O execute(I input) throws InvalidInputException;
}
