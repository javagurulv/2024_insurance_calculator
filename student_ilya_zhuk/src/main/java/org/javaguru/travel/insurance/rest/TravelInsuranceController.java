package org.javaguru.travel.insurance.rest;
import org.javaguru.travel.insurance.core.TravelCalculatePremiumService;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@RestController

@Validated

public class TravelInsuranceController {


    private TravelCalculatePremiumService premiumService;

    @PostMapping("/calculatePremium")
    public ResponseEntity<Object> calculatePremium(@Valid @RequestBody TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);
        return ResponseEntity.ok(response);
    }

    // Другие методы контроллера
}