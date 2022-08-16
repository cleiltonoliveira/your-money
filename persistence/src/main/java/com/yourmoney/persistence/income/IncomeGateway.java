package com.yourmoney.persistence.income;

import com.yourmoney.domain.model.Income;
import com.yourmoney.usecases.income.adapter.IncomeAdapter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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
        return repository.existsByDescriptionAndDateIsBetween(description, fromDate, toDate);
    }

    @Override
    public Optional<Income> findById(String id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Income> findByDescriptionAndDate(String description, LocalDate fromDate, LocalDate toDate) {
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
    public List<Income> findAllByDescriptionContaining(String description) {
        var result = repository.findAllByDescriptionContainingIgnoreCase(description);
        return result.stream().map(this::toDomain).collect(Collectors.toList());
    }

    private Income toDomain(IncomeEntity incomeEntity) {
        return modelMapper.map(incomeEntity, Income.class);
    }

    private IncomeEntity toEntity(Income income) {
        return modelMapper.map(income, IncomeEntity.class);
    }
}
