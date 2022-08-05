package com.example.usecases.expense;

import com.example.domain.model.Expense;
import com.example.usecases.exception.BadRequestException;
import com.example.usecases.exception.ResourceNotFoundException;
import com.example.usecases.expense.adapter.ExpenseAdapter;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;

@Named
public class ExpenseUpdater {
    private final ExpenseAdapter expenseAdapter;
    private final ExpenseFinder expenseFinder;

    @Inject
    public ExpenseUpdater(ExpenseAdapter expenseAdapter, ExpenseFinder expenseFinder) {
        this.expenseAdapter = expenseAdapter;
        this.expenseFinder = expenseFinder;
    }

    public Expense update(Expense expense) {
        verifyIfIncomeExists(expense.getId());
        verifyIfExistsIncomeInMonth(expense);
        return expenseAdapter.create(expense);
    }

    private void verifyIfIncomeExists(String id) {
        if (!expenseFinder.existsById(id)) {
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    private void verifyIfExistsIncomeInMonth(Expense expense) {

        var expenseDate = expense.getDate();
        var fromDate = LocalDate.of(expenseDate.getYear(), expenseDate.getMonthValue(), 1);
        var toDate = LocalDate.of(expenseDate.getYear(), expenseDate.getMonthValue(), expenseDate.lengthOfMonth());

        var expenseSearchResult = expenseAdapter.findByDescriptionAndDate(expense.getDescription(), fromDate, toDate);

        if (expenseSearchResult.isPresent()) {
            if (!expenseSearchResult.get().getId().equals(expense.getId())) {
                throw new BadRequestException("Expense is already registered in the month");
            }
        }
    }
}
