package com.hands_on.JDBC.repository;

import com.hands_on.JDBC.model.ShiftType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShiftTypeRepository {

    private final JdbcTemplate jdbcTemplate;

    public ShiftTypeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Insert ShiftType
    public void save(ShiftType shiftType) {
        String sql = "INSERT INTO Shift_Type (Name, Description, Active_Status, Tenant_ID) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, shiftType.getName(), shiftType.getDescription(), shiftType.getActiveStatus(), shiftType.getTenantId());
    }

    // Fetch all ShiftTypes for tenant
    public List<ShiftType> findAllByTenant(Long tenantId) {
        String sql = "SELECT * FROM Shift_Type WHERE Tenant_ID = ?";
        return jdbcTemplate.query(sql, new Object[]{tenantId}, (rs, rowNum) -> new ShiftType(
                rs.getLong("ID"),
                rs.getString("Name"),
                rs.getString("Description"),
                rs.getBoolean("Active_Status"),
                rs.getLong("Tenant_ID")
        ));
    }
}
