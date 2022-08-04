package com.example.usecases.income.adapter;

import com.example.domain.model.Income;

import java.time.LocalDate;
import java.util.List;

public interface IncomeAdapter {

    List<Income> findAll();

    Income create(Income income);

    boolean existsByDescriptionAndDate(String description, LocalDate fromDate, LocalDate toDate);
}
