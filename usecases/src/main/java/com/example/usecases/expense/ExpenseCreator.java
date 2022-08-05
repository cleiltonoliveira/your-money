package com.example.usecases.expense;

import com.example.domain.model.Expense;
import com.example.usecases.exception.BadRequestException;
import com.example.usecases.expense.adapter.ExpenseAdapter;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;

@Named
public class ExpenseCreator {
    private final ExpenseAdapter expenseAdapter;

    @Inject
    public ExpenseCreator(ExpenseAdapter expenseAdapter) {
        this.expenseAdapter = expenseAdapter;
    }

    public Expense create(Expense expense) {
        verifyIfExistsIncomeInMonth(expense);
        return expenseAdapter.create(expense);
    }

    private void verifyIfExistsIncomeInMonth(Expense expense) {

        var expenseDate = expense.getDate();
        var fromDate = LocalDate.of(expenseDate.getYear(), expenseDate.getMonthValue(), 1);
        var toDate = LocalDate.of(expenseDate.getYear(), expenseDate.getMonthValue(), expenseDate.lengthOfMonth());

        if (expenseAdapter.existsByDescriptionAndDate(expense.getDescription(), fromDate, toDate)) {
            throw new BadRequestException("Expense is already registered in the month");
        }
    }
}
