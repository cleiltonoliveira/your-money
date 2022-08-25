package com.yourmoney.usecases.income.adapter;

import com.yourmoney.domain.model.Income;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IncomeAdapter {

    List<Income> findAll();

    Income create(Income income);

    boolean existsByDescriptionAndDate(String description, LocalDate fromDate, LocalDate toDate);

    Optional<Income> findById(String id);

    Optional<Income> findByDescriptionAndDate(String description, LocalDate fromDate, LocalDate toDate);

    boolean existsById(String id);

    void deleteById(String id);

    List<Income> findAllByDescriptionContaining(String description);

    List<Income> findByDateBetween(LocalDate startDate, LocalDate endDate);

    BigDecimal findMonthIncomeAmount(LocalDate startDate, LocalDate endDate);
}
