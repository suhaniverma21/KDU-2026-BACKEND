package com.assessment.quickShip.controller;

import com.assessment.quickShip.entity.Package;
import com.assessment.quickShip.service.PackageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/packages")
public class PackageController {
    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }
    //fetching all the packages
    @GetMapping
    @PreAuthorize("hasAnyRole('MANAGER','DRIVER')")
    public List<Package> getAllPackages() {
        return packageService.getAllPackages();
    }


    //adding a new package
    @Operation(summary = "Starts the async package processing")
    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Package> addPackage(@RequestBody @Valid Package pck) {
        //book.setStatus("PROCESSING");
        Package savedPackage = packageService.addPackage(pck);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(savedPackage);
    }

    //finding the revenue of SORTED packages
    @GetMapping("/analytics/revenue")
    @PreAuthorize("hasAnyRole('MANAGER','DRIVER')")
    public Double getBooks() {
        return packageService.getRevenue();
    }
}
