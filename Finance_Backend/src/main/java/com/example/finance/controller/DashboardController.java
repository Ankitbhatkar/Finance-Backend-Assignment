package com.example.finance.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.finance.service.DashboardService;

import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService service;

    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    @GetMapping
    public Map<String, Object> summary() {
        return service.summary();
    }

    @GetMapping("/category")
    public Map<String, Double> categorySummary() {
        return service.categorySummary();
    }
}