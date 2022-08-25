package com.yourmoney.usecases.income;

import com.yourmoney.domain.model.Income;
import com.yourmoney.usecases.exception.ResourceNotFoundException;
import com.yourmoney.usecases.income.adapter.IncomeAdapter;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Named
public class IncomeFinder {
    private final IncomeAdapter incomeAdapter;

    @Inject
    public IncomeFinder(IncomeAdapter incomeAdapter) {
        this.incomeAdapter = incomeAdapter;
    }

    public List<Income> findIncomes() {
        return incomeAdapter.findAll();
    }

    public Income findById(String id) {
        return incomeAdapter.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }

    public boolean existsById(String id) {
        return incomeAdapter.existsById(id);
    }

    public List<Income> findIncomesByDescription(String description) {
        return incomeAdapter.findAllByDescriptionContaining(description);
    }

    public List<Income> findByYearMonth(int year, int month) {
        var fromDate = LocalDate.of(year, month, 1);
        var toDate = LocalDate.of(year, month, fromDate.lengthOfMonth());
        return incomeAdapter.findByDateBetween(fromDate, toDate);
    }

    public BigDecimal getMonthIncomeAmount(int year, int month) {

        var fromDate = LocalDate.of(year, month, 1);
        var toDate = LocalDate.of(year, month, fromDate.lengthOfMonth());
        return incomeAdapter.findMonthIncomeAmount(fromDate, toDate);
    }
}
