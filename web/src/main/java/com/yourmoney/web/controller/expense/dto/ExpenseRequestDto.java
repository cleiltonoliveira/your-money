package com.yourmoney.web.controller.expense.dto;

import com.yourmoney.domain.model.types.Category;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ExpenseRequestDto {
    @NotNull
    private BigDecimal amount;
    @NotBlank
    private String description;
    @NotNull
    private LocalDate date;
    private Category category = Category.OUTRAS;
}
