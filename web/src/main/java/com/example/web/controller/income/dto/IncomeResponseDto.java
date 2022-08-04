package com.example.web.controller.income.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class IncomeResponseDto {
    private BigDecimal amount;
    private String description;
    private LocalDate date;
}
