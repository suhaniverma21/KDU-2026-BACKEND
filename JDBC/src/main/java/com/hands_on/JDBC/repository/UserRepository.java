package com.hands_on.JDBC.repository;



import com.hands_on.JDBC.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Insert User
    public void save(User user) {
        String sql = "INSERT INTO Users (Username, LoggedIn_Status, TimeZone, Tenant_ID) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getLoggedInStatus(), user.getTimeZone(), user.getTenantId());
    }
    // PAGINATION + SORTING
    public List<User> findUsersByTenantWithPagination(
            Long tenantId,
            int page,
            int size,
            String sortDirection
    ) {

        int offset = page * size;

        // prevent SQL injection
        String order = sortDirection.equalsIgnoreCase("desc") ? "DESC" : "ASC";

        String sql = """
            SELECT * FROM Users
            WHERE Tenant_ID = ?
            ORDER BY Username %s
            LIMIT ? OFFSET ?
        """.formatted(order);

        return jdbcTemplate.query(
                sql,
                new Object[]{tenantId, size, offset},
                (rs, rowNum) -> new User(
                        rs.getLong("ID"),
                        rs.getString("Username"),
                        rs.getBoolean("LoggedIn_Status"),
                        rs.getString("TimeZone"),
                        rs.getLong("Tenant_ID")
                )
        );}
    // Fetch all users for tenant
    public List<User> findAllByTenant(Long tenantId) {
        String sql = "SELECT * FROM Users WHERE Tenant_ID = ?";
        return jdbcTemplate.query(sql, new Object[]{tenantId}, (rs, rowNum) -> new User(
                rs.getLong("ID"),
                rs.getString("Username"),
                rs.getBoolean("LoggedIn_Status"),
                rs.getString("TimeZone"),
                rs.getLong("Tenant_ID")
        ));
    }


    // Update user
    public void update(User user) {
        String sql = "UPDATE Users SET Username = ?, TimeZone = ? WHERE ID = ?";
        jdbcTemplate.update(sql, user.getUsername(), user.getTimeZone(), user.getId());
    }
}

