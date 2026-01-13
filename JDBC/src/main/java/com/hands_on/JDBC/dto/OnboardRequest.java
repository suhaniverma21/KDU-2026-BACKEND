package com.hands_on.JDBC.dto;

import com.hands_on.JDBC.model.Shift;
import com.hands_on.JDBC.model.ShiftType;
import com.hands_on.JDBC.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnboardRequest {

    private ShiftType shiftType;
    private Shift shift;
    private User user;

}
