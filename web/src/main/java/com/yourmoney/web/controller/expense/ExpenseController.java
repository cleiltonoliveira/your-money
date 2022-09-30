package com.yourmoney.web.controller.expense;

import com.yourmoney.domain.model.Expense;
import com.yourmoney.usecases.expense.ExpenseCreator;
import com.yourmoney.usecases.expense.ExpenseDeleter;
import com.yourmoney.usecases.expense.ExpenseFinder;
import com.yourmoney.usecases.expense.ExpenseUpdater;
import com.yourmoney.web.controller.expense.dto.ExpenseRequestDto;
import com.yourmoney.web.controller.expense.dto.ExpenseResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1")
public class ExpenseController {

    private final ExpenseFinder expenseFinder;
    private final ExpenseCreator expenseCreator;
    private final ExpenseUpdater expenseUpdater;
    private final ExpenseDeleter expenseDeleter;
    private final ModelMapper modelMapper;

    public ExpenseController(ExpenseFinder expenseFinder,
                             ExpenseCreator expenseCreator,
                             ExpenseUpdater expenseUpdater,
                             ExpenseDeleter expenseDeleter,
                             ModelMapper modelMapper) {
        this.expenseFinder = expenseFinder;
        this.expenseCreator = expenseCreator;
        this.expenseUpdater = expenseUpdater;
        this.expenseDeleter = expenseDeleter;
        this.modelMapper = modelMapper;
    }

    @GetMapping("despesas")
    public ResponseEntity<List<ExpenseResponseDto>> findExpenses(@RequestParam(value = "descricao", required = false) String description) {
        if (description != null)
            return new ResponseEntity<>(expenseFinder.findExpensesByDescription(description).stream().map(this::toDto).collect(Collectors.toList()), HttpStatus.OK);
        return new ResponseEntity<>(expenseFinder.findExpenses().stream().map(this::toDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("despesas/{id}")
    public ResponseEntity<ExpenseResponseDto> findById(@PathVariable("id") String id) {
        return new ResponseEntity<>(toDto(expenseFinder.findById(id)), HttpStatus.OK);
    }

    @GetMapping("despesas/{year}/{month}")
    public ResponseEntity<List<ExpenseResponseDto>> findByYearMonth(@PathVariable("year") int year, @PathVariable("month") @Min(value = 1, message = "Invalid month") @Max(value = 12, message = "Invalid month") int month) {
        return new ResponseEntity<>(expenseFinder.findByYearMonth(year, month).stream().map(this::toDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("despesas")
    public ResponseEntity<ExpenseResponseDto> createExpense(@RequestBody @Valid ExpenseRequestDto ExpenseDto) {
        return new ResponseEntity<>(toDto(expenseCreator.create(toDomain(ExpenseDto))), HttpStatus.CREATED);
    }

    @PutMapping("despesas/{id}")
    public ResponseEntity<ExpenseResponseDto> updateExpense(@PathVariable("id") String id, @RequestBody @Valid ExpenseRequestDto expenseDto) {
        var expense = toDomain(expenseDto);
        expense.setId(id);
        return new ResponseEntity<>(toDto(expenseUpdater.update(expense)), HttpStatus.OK);
    }

    @DeleteMapping("despesas/{id}")
    public ResponseEntity<ExpenseResponseDto> deleteExpense(@PathVariable("id") String id) {
        expenseDeleter.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Expense toDomain(ExpenseRequestDto dto) {
        return modelMapper.map(dto, Expense.class);
    }

    private ExpenseResponseDto toDto(Expense expense) {
        return modelMapper.map(expense, ExpenseResponseDto.class);
    }
}
