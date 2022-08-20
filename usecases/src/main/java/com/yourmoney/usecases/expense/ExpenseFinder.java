package com.yourmoney.usecases.expense;

import com.yourmoney.domain.model.Expense;
import com.yourmoney.usecases.exception.ResourceNotFoundException;
import com.yourmoney.usecases.expense.adapter.ExpenseAdapter;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;
import java.util.List;

@Named
public class ExpenseFinder {
    private final ExpenseAdapter expenseAdapter;

    @Inject
    public ExpenseFinder(ExpenseAdapter expenseAdapter) {
        this.expenseAdapter = expenseAdapter;
    }

    public List<Expense> findExpenses() {
        return expenseAdapter.findAll();
    }

    public Expense findById(String id) {
        return expenseAdapter.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }

    public boolean existsById(String id) {
        return expenseAdapter.existsById(id);
    }

    public List<Expense> findExpensesByDescription(String description) {
        return expenseAdapter.findAllByDescriptionContaining(description);
    }

    public List<Expense> findByYearMonth(int year, int month) {
        var fromDate = LocalDate.of(year, month, 1);
        var toDate = LocalDate.of(year, month, fromDate.lengthOfMonth());
        return expenseAdapter.findByDateBetween(fromDate, toDate);
    }
}
