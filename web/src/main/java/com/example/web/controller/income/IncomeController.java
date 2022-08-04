package com.example.web.controller.income;

import com.example.domain.model.Income;
import com.example.usecases.income.IncomeFinder;
import com.example.usecases.income.IncomeCreator;
import com.example.web.controller.income.dto.IncomeRequestDto;
import com.example.web.controller.income.dto.IncomeResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1")
public class IncomeController {

    private final IncomeFinder incomeFinder;
    private final IncomeCreator incomeCreator;
    private final ModelMapper modelMapper;

    public IncomeController(IncomeFinder incomeFinder, IncomeCreator incomeCreator, ModelMapper modelMapper) {
        this.incomeFinder = incomeFinder;
        this.incomeCreator = incomeCreator;
        this.modelMapper = modelMapper;
    }

    @GetMapping("receitas")
    public ResponseEntity<List<IncomeResponseDto>> findIncomes() {
        return new ResponseEntity<>(incomeFinder.findIncomes().stream().map(this::toDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("receitas")
    public ResponseEntity<IncomeResponseDto> createIncome(@RequestBody @Valid IncomeRequestDto incomeDto) {
        return new ResponseEntity<>(toDto(incomeCreator.create(toDomain(incomeDto))), HttpStatus.OK);
    }

    private Income toDomain(IncomeRequestDto dto) {
        return modelMapper.map(dto, Income.class);
    }

    private IncomeResponseDto toDto(Income income) {
        return modelMapper.map(income, IncomeResponseDto.class);
    }
}
