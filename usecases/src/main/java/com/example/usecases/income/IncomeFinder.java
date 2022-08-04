package com.example.usecases.income;

import com.example.domain.model.Income;
import com.example.usecases.exception.ResourceNotFoundException;
import com.example.usecases.income.adapter.IncomeAdapter;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class IncomeFinder {
    private final IncomeAdapter incomeAdapter;

    @Inject
    public IncomeFinder(IncomeAdapter incomeAdapter) {
        this.incomeAdapter = incomeAdapter;
    }

    public List<Income> findIncomes(){
        return  incomeAdapter.findAll();
    }

    public Income findById(String id) {
        return incomeAdapter.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource not found"));
    }
}
