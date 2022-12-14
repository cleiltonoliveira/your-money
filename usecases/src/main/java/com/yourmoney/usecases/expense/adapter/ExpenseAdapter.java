package com.yourmoney.usecases.expense.adapter;

import com.yourmoney.domain.model.Expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ExpenseAdapter {

    List<Expense> findAll();

    Expense create(Expense expense);

    boolean existsByDescriptionAndDate(String description, LocalDate fromDate, LocalDate toDate);

    Optional<Expense> findById(String id);

    Optional<Expense> findByDescriptionAndDate(String description, LocalDate fromDate, LocalDate toDate);

    boolean existsById(String id);

    void deleteById(String id);

    List<Expense> findAllByDescriptionContaining(String description);

    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);

    Map<String, BigDecimal> findMonthExpenseResume(LocalDate startDate, LocalDate endDate);
}
