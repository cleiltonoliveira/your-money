package com.example.usecases.income;

import com.example.domain.model.Income;
import com.example.usecases.exception.BadRequestException;
import com.example.usecases.exception.ResourceNotFoundException;
import com.example.usecases.income.adapter.IncomeAdapter;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;

@Named
public class IncomeUpdater {
    private final IncomeAdapter incomeAdapter;
    private final IncomeFinder incomeFinder;

    @Inject
    public IncomeUpdater(IncomeAdapter incomeAdapter, IncomeFinder incomeFinder) {
        this.incomeAdapter = incomeAdapter;
        this.incomeFinder = incomeFinder;
    }

    public Income update(Income income) {
        verifyIfIncomeExists(income.getId());
        verifyIfExistsIncomeInMonth(income);
        return incomeAdapter.create(income);
    }

    private void verifyIfIncomeExists(String id) {
        if (!incomeFinder.existsById(id)) {
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    private void verifyIfExistsIncomeInMonth(Income income) {

        var incomeDate = income.getDate();
        var fromDate = LocalDate.of(incomeDate.getYear(), incomeDate.getMonthValue(), 1);
        var toDate = LocalDate.of(incomeDate.getYear(), incomeDate.getMonthValue(), incomeDate.lengthOfMonth());

        var incomeSearchResult = incomeAdapter.findByDescriptionAndDate(income.getDescription(), fromDate, toDate);

       if (incomeSearchResult.isPresent()){
           if (!incomeSearchResult.get().getId().equals(income.getId())) {
               throw new BadRequestException("Income is already registered in the month");
           }
       }
    }
}
