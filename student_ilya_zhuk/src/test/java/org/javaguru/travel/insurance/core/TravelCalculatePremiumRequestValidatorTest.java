package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.datavalidator.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.datavalidator.ValidatorError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.xml.validation.Validator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelCalculatePremiumRequestValidatorTest {

    @Mock
    private TravelCalculatePremiumRequest mockRequest;

    @InjectMocks
    private TravelCalculatePremiumRequestValidator validator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testValidate_withValidRequest() {
        when(mockRequest.getPersonFirstname()).thenReturn("John");
        when(mockRequest.getPersonLastname()).thenReturn("Doe");
        when(mockRequest.getAgreementDateFrom()).thenReturn(new Date());
        when(mockRequest.getAgreementDateTo()).thenReturn(new Date(System.currentTimeMillis() + 1000));

        List<ValidatorError> errors = validator.validate(mockRequest);

        assertThat(errors).isEmpty();
    }

    @Test
    public void testValidate_withMissingFirstName() {
        when(mockRequest.getPersonFirstname()).thenReturn(null);
        when(mockRequest.getPersonLastname()).thenReturn("Doe");
        when(mockRequest.getAgreementDateFrom()).thenReturn(new Date());
        when(mockRequest.getAgreementDateTo()).thenReturn(new Date(System.currentTimeMillis() + 1000));

        List<ValidatorError> errors = validator.validate(mockRequest);

        assertThat(errors).hasSize(1);
        assertThat(errors.get(0).getField()).isEqualTo("personFirstName");
        assertThat(errors.get(0).getMessage()).isEqualTo("Must not be empty!");
    }


    @Test
    void testValidate_withMissingAgreementDateFrom() {
        // Arrange
        TravelCalculatePremiumRequest mockRequest = mock(TravelCalculatePremiumRequest.class);
        when(mockRequest.getPersonFirstname()).thenReturn("John");
        when(mockRequest.getPersonLastname()).thenReturn("Doe");
        when(mockRequest.getAgreementDateFrom()).thenReturn(null); // Установим null для agreementDateFrom
        when(mockRequest.getAgreementDateTo()).thenReturn(new Date(System.currentTimeMillis() + 1000)); // Установим будущую дату

        TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();

        // Act
        List<ValidatorError> errors = validator.validate(mockRequest);

        // Assert
        assertThat(errors).hasSize(1);
        assertThat(errors.get(0).getField()).isEqualTo("agreementDates");
        assertThat(errors.get(0).getMessage()).isEqualTo("Date From must not be null!");
    }

}







