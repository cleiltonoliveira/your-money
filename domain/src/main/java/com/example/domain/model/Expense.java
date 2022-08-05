package com.example.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class Expense {
    private String id;
    private String description;
    private BigDecimal amount;
    private LocalDate date;
}
