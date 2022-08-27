package com.yourmoney.persistence.income;

import com.yourmoney.domain.model.Income;
import com.yourmoney.usecases.income.adapter.IncomeAdapter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class IncomeGateway implements IncomeAdapter {

    private final ModelMapper modelMapper;
    private final IncomeRepository repository;

    public IncomeGateway(ModelMapper modelMapper, IncomeRepository repository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Override
    public List<Income> findAll() {
        var result = repository.findAll();
        return result.stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public Income create(Income income) {
        return toDomain(repository.save(toEntity(income)));
    }

    @Override
    public boolean existsByDescriptionAndDate(String description, LocalDate fromDate, LocalDate toDate) {
        return repository.existsByDescriptionAndDateBetween(description, fromDate, toDate);
    }

    @Override
    public Optional<Income> findById(String id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Income> findByDescriptionAndDate(String description, LocalDate fromDate, LocalDate toDate) {
        return repository.findByDescriptionAndDateBetween(description, fromDate, toDate).map(this::toDomain);
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
    public List<Income> findAllByDescriptionContaining(String description) {
        var result = repository.findAllByDescriptionContainingIgnoreCase(description);
        return result.stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Income> findByDateBetween(LocalDate startDate, LocalDate endDate) {
        var result = repository.findAllByDateBetween(startDate, endDate);
        return result.stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public BigDecimal findMonthIncomeAmount(LocalDate startDate, LocalDate endDate) {
        var amount = repository.findMonthIncomeAmount(startDate, endDate);
        return amount.bigDecimalValue();
    }

    private Income toDomain(IncomeEntity incomeEntity) {
        return modelMapper.map(incomeEntity, Income.class);
    }

    private IncomeEntity toEntity(Income income) {
        return modelMapper.map(income, IncomeEntity.class);
    }
}
