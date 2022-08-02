package com.example.persistence.income;

import com.example.domain.model.Income;
import com.example.usecases.income.adapter.IncomeAdapter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
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
        return  result.stream().map(this::toDomain).collect(Collectors.toList()) ;
    }

    private Income toDomain(IncomeEntity incomeEntity) {
        return  modelMapper.map(incomeEntity, Income.class);
    }
}
