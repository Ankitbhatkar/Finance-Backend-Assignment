package com.example.finance.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.finance.dto.RecordDTO;
import com.example.finance.entity.FinancialRecord;
import com.example.finance.repository.FinancialRecordRepository;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class FinancialService {

    private final FinancialRecordRepository repo;

    
    public FinancialRecord create(RecordDTO dto) {
        FinancialRecord record = new FinancialRecord();
        BeanUtils.copyProperties(dto, record);
        return repo.save(record);
    }

   
    public List<FinancialRecord> getAll(String type,
                                        String category,
                                        LocalDate startDate,
                                        LocalDate endDate) {

        List<FinancialRecord> records = repo.findAll();

        
        if (type != null) {
            records = records.stream()
                    .filter(r -> r.getType().equalsIgnoreCase(type))
                    .toList();
        }

        if (category != null) {
            records = records.stream()
                    .filter(r -> r.getCategory() != null &&
                            r.getCategory().equalsIgnoreCase(category))
                    .toList();
        }

        if (startDate != null && endDate != null) {
            records = records.stream()
                    .filter(r -> r.getDate() != null &&
                            !r.getDate().isBefore(startDate) &&
                            !r.getDate().isAfter(endDate))
                    .toList();
        }

        return records;
    }

  
    public FinancialRecord getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found with id: " + id));
    }


    public FinancialRecord update(Long id, RecordDTO dto) {

        FinancialRecord record = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        BeanUtils.copyProperties(dto, record);

        return repo.save(record);
    }

    
    public void delete(Long id) {
        FinancialRecord record = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        repo.delete(record);
    }
}