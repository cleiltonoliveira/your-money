package com.yourmoney.persistence.expense;

import com.yourmoney.domain.model.Expense;
import com.yourmoney.usecases.expense.adapter.ExpenseAdapter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ExpenseGateway implements ExpenseAdapter {

    private final ModelMapper modelMapper;
    private final ExpenseRepository repository;

    public ExpenseGateway(ModelMapper modelMapper, ExpenseRepository repository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Override
    public List<Expense> findAll() {
        var result = repository.findAll();
        return result.stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public Expense create(Expense expense) {
        return toDomain(repository.save(toEntity(expense)));
    }

    @Override
    public boolean existsByDescriptionAndDate(String description, LocalDate fromDate, LocalDate toDate) {
        return repository.existsByDescriptionAndDateIsBetween(description, fromDate, toDate);
    }

    @Override
    public Optional<Expense> findById(String id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Expense> findByDescriptionAndDate(String description, LocalDate fromDate, LocalDate toDate) {
        return repository.findByDescriptionAndDateIsBetween(description, fromDate, toDate).map(this::toDomain);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Expense> findAllByDescriptionContaining(String description) {
        var result = repository.findAllByDescriptionContainingIgnoreCase(description);
        return result.stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate) {
        var result = repository.findAllByDateBetween(startDate, endDate);
        return result.stream().map(this::toDomain).collect(Collectors.toList());
    }

    private Expense toDomain(ExpenseEntity expenseEntity) {
        return modelMapper.map(expenseEntity, Expense.class);
    }

    private ExpenseEntity toEntity(Expense expense) {
        return modelMapper.map(expense, ExpenseEntity.class);
    }
}
