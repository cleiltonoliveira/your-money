package com.yourmoney.web.controller.expense.dto;

import com.yourmoney.domain.model.types.Category;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ExpenseResponseDto {
    private BigDecimal amount;
    private String description;
    private LocalDate date;
    private Category category;
}
