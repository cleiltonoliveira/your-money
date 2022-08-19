package com.yourmoney.web.controller.income;

import com.yourmoney.domain.model.Income;
import com.yourmoney.usecases.income.IncomeDeleter;
import com.yourmoney.usecases.income.IncomeFinder;
import com.yourmoney.usecases.income.IncomeCreator;
import com.yourmoney.usecases.income.IncomeUpdater;
import com.yourmoney.web.controller.income.dto.IncomeRequestDto;
import com.yourmoney.web.controller.income.dto.IncomeResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1")
@Validated
public class IncomeController {

    private final IncomeFinder incomeFinder;
    private final IncomeCreator incomeCreator;
    private final IncomeUpdater incomeUpdater;
    private final IncomeDeleter incomeDeleter;
    private final ModelMapper modelMapper;

    public IncomeController(IncomeFinder incomeFinder,
                            IncomeCreator incomeCreator,
                            IncomeUpdater incomeUpdater,
                            IncomeDeleter incomeDeleter,
                            ModelMapper modelMapper) {
        this.incomeFinder = incomeFinder;
        this.incomeCreator = incomeCreator;
        this.incomeUpdater = incomeUpdater;
        this.incomeDeleter = incomeDeleter;
        this.modelMapper = modelMapper;
    }

    @GetMapping("receitas")
    public ResponseEntity<List<IncomeResponseDto>> findIncomes(@RequestParam("descricao") String description) {
        if (description != null)
            return new ResponseEntity<>(incomeFinder.findIncomesByDescription(description).stream().map(this::toDto).collect(Collectors.toList()), HttpStatus.OK);
        return new ResponseEntity<>(incomeFinder.findIncomes().stream().map(this::toDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("receitas/{id}")
    public ResponseEntity<IncomeResponseDto> findById(@PathVariable("id") String id) {
        return new ResponseEntity<>(toDto(incomeFinder.findById(id)), HttpStatus.OK);
    }

    @GetMapping("receitas/{year}/{month}")
    public ResponseEntity<List<IncomeResponseDto>> findByYearMonth(@PathVariable("year") int year, @PathVariable("month") @Min(value = 1, message = "Invalid month") @Max(value = 12, message = "Invalid month") int month) {
        return new ResponseEntity<>(incomeFinder.findByYearMonth(year, month).stream().map(this::toDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("receitas")
    public ResponseEntity<IncomeResponseDto> createIncome(@RequestBody @Valid IncomeRequestDto incomeDto) {
        return new ResponseEntity<>(toDto(incomeCreator.create(toDomain(incomeDto))), HttpStatus.CREATED);
    }

    @PutMapping("receitas/{id}")
    public ResponseEntity<IncomeResponseDto> updateIncome(@PathVariable("id") String id, @RequestBody @Valid IncomeRequestDto incomeDto) {
        var income = toDomain(incomeDto);
        income.setId(id);
        return new ResponseEntity<>(toDto(incomeUpdater.update(income)), HttpStatus.OK);
    }

    @DeleteMapping("receitas/{id}")
    public ResponseEntity<IncomeResponseDto> deleteIncome(@PathVariable("id") String id) {
        incomeDeleter.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Income toDomain(IncomeRequestDto dto) {
        return modelMapper.map(dto, Income.class);
    }

    private IncomeResponseDto toDto(Income income) {
        return modelMapper.map(income, IncomeResponseDto.class);
    }
}
