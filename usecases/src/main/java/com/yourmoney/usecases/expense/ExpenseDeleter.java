package com.yourmoney.usecases.expense;

import com.yourmoney.usecases.expense.adapter.ExpenseAdapter;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ExpenseDeleter {
    private final ExpenseAdapter expenseAdapter;

    @Inject
    public ExpenseDeleter(ExpenseAdapter expenseAdapter) {
        this.expenseAdapter = expenseAdapter;
    }

    public void delete(String id) {
        expenseAdapter.deleteById(id);
    }
}
