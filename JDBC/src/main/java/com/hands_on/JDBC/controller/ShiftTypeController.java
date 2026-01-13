package com.hands_on.JDBC.controller;

import com.hands_on.JDBC.model.ShiftType;
import com.hands_on.JDBC.repository.ShiftTypeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shift-types")
public class ShiftTypeController {

    private final ShiftTypeRepository repo;

    public ShiftTypeController(ShiftTypeRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public String createShiftType(@RequestBody ShiftType shiftType) {
        repo.save(shiftType);
        return "ShiftType created successfully!";
    }

    @GetMapping("/tenant/{tenantId}")
    public List<ShiftType> getShiftTypesByTenant(@PathVariable Long tenantId) {
        return repo.findAllByTenant(tenantId);
    }
}
