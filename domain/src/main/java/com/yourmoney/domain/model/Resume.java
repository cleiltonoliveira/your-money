package com.yourmoney.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Resume {
    private BigDecimal incomeAmount;
    private BigDecimal expenseAmount;
    private BigDecimal balance;
    private Map<String, BigDecimal> categoriesExpend;
}
