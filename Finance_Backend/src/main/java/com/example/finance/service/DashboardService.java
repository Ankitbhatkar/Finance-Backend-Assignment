package com.example.finance.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.finance.entity.FinancialRecord;
import com.example.finance.repository.FinancialRecordRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final FinancialRecordRepository repo;

    public Map<String, Object> summary() {

        List<FinancialRecord> list = repo.findAll();

        double income = list.stream()
                .filter(r -> r.getType().equals("INCOME"))
                .mapToDouble(FinancialRecord::getAmount)
                .sum();

        double expense = list.stream()
                .filter(r -> r.getType().equals("EXPENSE"))
                .mapToDouble(FinancialRecord::getAmount)
                .sum();

        Map<String, Object> map = new HashMap<>();
        map.put("income", income);
        map.put("expense", expense);
        map.put("balance", income - expense);
        map.put("recent", list.stream().limit(5).toList());

        return map;
    }

    public Map<String, Double> categorySummary() {

        List<Object[]> data = repo.categorySummary();

        Map<String, Double> map = new HashMap<>();

        for (Object[] obj : data) {
            map.put((String) obj[0], (Double) obj[1]);
        }

        return map;
    }
}