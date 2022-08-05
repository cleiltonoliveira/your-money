package com.yourmoney.usecases.income;

import com.yourmoney.domain.model.Income;
import com.yourmoney.usecases.exception.BadRequestException;
import com.yourmoney.usecases.income.adapter.IncomeAdapter;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;

@Named
public class IncomeCreator {
    private final IncomeAdapter incomeAdapter;

    @Inject
    public IncomeCreator(IncomeAdapter incomeAdapter) {
        this.incomeAdapter = incomeAdapter;
    }

    public Income create(Income income) {
        verifyIfExistsIncomeInMonth(income);
        return incomeAdapter.create(income);
    }

    private void verifyIfExistsIncomeInMonth(Income income) {

        var incomeDate = income.getDate();
        var fromDate = LocalDate.of(incomeDate.getYear(), incomeDate.getMonthValue(), 1);
        var toDate = LocalDate.of(incomeDate.getYear(), incomeDate.getMonthValue(), incomeDate.lengthOfMonth());

        if (incomeAdapter.existsByDescriptionAndDate(income.getDescription(), fromDate, toDate)) {
            throw new BadRequestException("Income is already registered in the month");
        }
    }
}
