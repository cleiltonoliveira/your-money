package com.yourmoney.web.controller.resume.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
public class ResumeDto {
    private BigDecimal incomeAmount;
    private BigDecimal expenseAmount;
    private BigDecimal balance;
    private Map<String, BigDecimal> categoriesExpend;
}
