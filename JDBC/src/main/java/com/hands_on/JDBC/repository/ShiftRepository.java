package com.hands_on.JDBC.repository;

import com.hands_on.JDBC.model.Shift;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShiftRepository {

    private final JdbcTemplate jdbcTemplate;

    public ShiftRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // INSERT SHIFT
    public void save(Shift shift) {

        String sql = """
            INSERT INTO Shift
            (Shift_Type_ID, Date_Start, Date_End, Time_Start, Time_End, Tenant_ID)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(
                sql,
                shift.getShiftTypeId(),
                shift.getDateStart(),
                shift.getDateEnd(),
                shift.getTimeStart(),
                shift.getTimeEnd(),
                shift.getTenantId()
        );
    }
}
