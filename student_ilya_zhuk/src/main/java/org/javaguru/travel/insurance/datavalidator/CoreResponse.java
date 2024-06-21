package org.javaguru.travel.insurance.datavalidator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CoreResponse {
    private List<ValidatorError> validatorErrors;

    public boolean hasErrors() {
        return validatorErrors != null && !validatorErrors.isEmpty();
    }
}
