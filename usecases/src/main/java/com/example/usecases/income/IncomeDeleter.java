package com.example.usecases.income;

import com.example.domain.model.Income;
import com.example.usecases.exception.ResourceNotFoundException;
import com.example.usecases.income.adapter.IncomeAdapter;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class IncomeDeleter {
    private final IncomeAdapter incomeAdapter;

    @Inject
    public IncomeDeleter(IncomeAdapter incomeAdapter) {
        this.incomeAdapter = incomeAdapter;
    }

    public List<Income> findIncomes(){
        return  incomeAdapter.findAll();
    }

    public void delete(String id) {
        incomeAdapter.deleteById(id);
    }
}
