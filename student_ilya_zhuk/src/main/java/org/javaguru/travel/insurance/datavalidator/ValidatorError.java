package org.javaguru.travel.insurance.datavalidator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ValidatorError {

    private String field;
    private String message;
}
