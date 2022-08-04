package com.example.web.controller.income.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class IncomeResponseDto {
    private String amount;
    private String description;
    private LocalDate date;
}
