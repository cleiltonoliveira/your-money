package com.yourmoney.usecases.resume;

import com.yourmoney.domain.model.Resume;
import com.yourmoney.usecases.expense.ExpenseFinder;
import com.yourmoney.usecases.income.IncomeFinder;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;

@Named
public class ResumeFinder {

    private ExpenseFinder expenseFinder;
    private IncomeFinder incomeFinder;

    @Inject
    public ResumeFinder(ExpenseFinder expenseFinder, IncomeFinder incomeFinder) {
        this.expenseFinder = expenseFinder;
        this.incomeFinder = incomeFinder;
    }

    public Resume getMonthFinanceResume(int year, int month) {
        var incomeAmount = incomeFinder.getMonthIncomeAmount(year, month);
        var expensesByCategory = expenseFinder.getMonthExpenseResume(year, month);
        var expenseAmount = expensesByCategory.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        var balance = incomeAmount.subtract(expenseAmount);

        var resume = new Resume(incomeAmount, expenseAmount, balance, expensesByCategory);

        return resume;
    }
}
