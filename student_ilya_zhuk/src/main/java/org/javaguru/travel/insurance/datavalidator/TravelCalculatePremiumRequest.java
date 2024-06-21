package org.javaguru.travel.insurance.datavalidator;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumRequest{

    private String personFirstname;
    private String personLastname;
    private Date agreementDateFrom;
    private Date agreementDateTo;

}
