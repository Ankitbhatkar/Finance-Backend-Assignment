package com.example.finance.dto;



import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RecordDTO {

    @NotNull
    @Positive
    private Double amount;

    @NotBlank
    private String type;

    private String category;
    private LocalDate date;
    private String description;
}