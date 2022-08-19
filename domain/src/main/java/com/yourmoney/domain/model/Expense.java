package com.yourmoney.domain.model;

import com.yourmoney.domain.model.types.Category;
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
    private Category category;
}
