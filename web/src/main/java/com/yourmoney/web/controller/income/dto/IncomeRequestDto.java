package com.yourmoney.web.controller.income.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class IncomeRequestDto {
    @NotNull
    private BigDecimal amount;
    @NotBlank
    private String description;
    @NotNull
    private LocalDate date;
}
