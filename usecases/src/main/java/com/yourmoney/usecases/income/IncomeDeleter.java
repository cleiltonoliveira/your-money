package com.yourmoney.usecases.income;

import com.yourmoney.usecases.income.adapter.IncomeAdapter;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class IncomeDeleter {
    private final IncomeAdapter incomeAdapter;

    @Inject
    public IncomeDeleter(IncomeAdapter incomeAdapter) {
        this.incomeAdapter = incomeAdapter;
    }

    public void delete(String id) {
        incomeAdapter.deleteById(id);
    }
}
