package com.hands_on.JDBC.service;

import com.hands_on.JDBC.model.Shift;
import com.hands_on.JDBC.model.ShiftType;
import com.hands_on.JDBC.model.User;
import com.hands_on.JDBC.repository.ShiftTypeRepository;
import com.hands_on.JDBC.repository.ShiftRepository;

import com.hands_on.JDBC.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OnboardingService {

    private final ShiftTypeRepository shiftTypeRepository;
    private final ShiftRepository shiftRepository;
    private final UserRepository userRepository;

    public OnboardingService(
            ShiftTypeRepository shiftTypeRepository,
            ShiftRepository shiftRepository,
            UserRepository userRepository) {
        this.shiftTypeRepository = shiftTypeRepository;
        this.shiftRepository = shiftRepository;
        this.userRepository = userRepository;
    }

    // 🔥 ALL-OR-NOTHING METHOD
    @Transactional
    public void onboardHospitalBranch(
            ShiftType shiftType,
            Shift shift,
            User user
    ) {

        shiftTypeRepository.save(shiftType);

        shiftRepository.save(shift);

        userRepository.save(user);

        if (user.getUsername() == null) {
            throw new RuntimeException("Forcing rollback");
        }
    }
}
