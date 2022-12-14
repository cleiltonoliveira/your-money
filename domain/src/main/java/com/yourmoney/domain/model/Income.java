package com.yourmoney.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class Income {
    private String id;
    private String description;
    private BigDecimal amount;
    private LocalDate date;
}
