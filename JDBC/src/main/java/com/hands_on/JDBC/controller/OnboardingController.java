package com.hands_on.JDBC.controller;

import com.hands_on.JDBC.dto.OnboardRequest;
import com.hands_on.JDBC.service.OnboardingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/onboard")
public class OnboardingController {

    private final OnboardingService onboardingService;

    public OnboardingController(OnboardingService onboardingService) {
        this.onboardingService = onboardingService;
    }

    @PostMapping
    public String onboard(@RequestBody OnboardRequest request) {
        onboardingService.onboardHospitalBranch(
                request.getShiftType(),
                request.getShift(),
                request.getUser()
        );
        return "Onboarding successful";
    }
}

