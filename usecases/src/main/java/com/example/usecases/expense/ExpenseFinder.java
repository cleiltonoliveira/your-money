package com.example.usecases.expense;

import com.example.domain.model.Expense;
import com.example.usecases.exception.ResourceNotFoundException;
import com.example.usecases.expense.adapter.ExpenseAdapter;

import javax.inject.Inject;
import javax.inject.Named;
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
}
