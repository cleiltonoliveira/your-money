package com.yourmoney.usecases.income;

import com.yourmoney.domain.model.Income;
import com.yourmoney.usecases.exception.ResourceNotFoundException;
import com.yourmoney.usecases.income.adapter.IncomeAdapter;

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

    public boolean existsById(String id) {
        return  incomeAdapter.existsById(id);
    }
}
