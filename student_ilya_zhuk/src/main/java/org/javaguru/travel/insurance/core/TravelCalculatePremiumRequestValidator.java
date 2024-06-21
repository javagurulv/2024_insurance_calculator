package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.datavalidator.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.datavalidator.ValidatorError;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

class TravelCalculatePremiumRequestValidator   {

    public List<ValidatorError> validate(TravelCalculatePremiumRequest request) {
        List<ValidatorError> errors = new ArrayList<>();
        validatePersonFirstName(request).ifPresent(errors::add);
        validatePersonLastname(request).ifPresent(errors::add);
        validateAgreementDateFromTo(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<ValidatorError> validatePersonFirstName(TravelCalculatePremiumRequest request) {
        return (request.getPersonFirstname() == null || request.getPersonLastname().trim().isEmpty())
                ? Optional.of(new ValidatorError("personFirstName", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidatorError> validatePersonLastname(TravelCalculatePremiumRequest request) {
        if (request.getPersonLastname() == null || request.getPersonLastname().trim().isEmpty()) {
            return Optional.of(new ValidatorError("personLastname", "Must not be empty!"));
        }
        return Optional.empty();
    }
    private Optional<ValidatorError> validateAgreementDateFromTo(TravelCalculatePremiumRequest request) {
        Date agreementDateFrom = request.getAgreementDateFrom();
        Date agreementDateTo = request.getAgreementDateTo();

        if (agreementDateFrom == null || agreementDateTo == null) {
            return Optional.empty();
        }

        if (agreementDateFrom.after(agreementDateTo)) {
            return Optional.of(new ValidatorError("agreementDates", "Date From must not be after Date To!"));
        }

        return Optional.empty();
    }
    private boolean isValidDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("30.02");

        dateFormat.setLenient(false);

        try {
            dateFormat.parse(dateFormat.format(date));
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}