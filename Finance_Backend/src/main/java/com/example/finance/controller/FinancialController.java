package com.example.finance.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.finance.dto.RecordDTO;
import com.example.finance.entity.FinancialRecord;
import com.example.finance.service.FinancialService;

import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/records")
@RequiredArgsConstructor
public class FinancialController {

    private final FinancialService service;

    
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    @PostMapping
    public FinancialRecord create(@Valid @RequestBody RecordDTO dto) {
        return service.create(dto);
    }

   
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    @GetMapping
    public List<FinancialRecord> getAll(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate
    ) {

        LocalDate start = (startDate != null) ? LocalDate.parse(startDate) : null;
        LocalDate end = (endDate != null) ? LocalDate.parse(endDate) : null;

        return service.getAll(type, category, start, end);
    }

    
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    @GetMapping("/{id}")
    public FinancialRecord getById(@PathVariable Long id) {
        return service.getById(id);
    }

    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public FinancialRecord update(
            @PathVariable Long id,
            @Valid @RequestBody RecordDTO dto
    ) {
        return service.update(id, dto);
    }

    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Record deleted successfully";
    }
}