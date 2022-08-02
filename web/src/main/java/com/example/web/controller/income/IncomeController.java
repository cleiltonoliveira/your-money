package com.example.web.controller.income;

import com.example.usecases.income.IncomeFinder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class IncomeController {

    private IncomeFinder incomeFinder;

    public IncomeController(IncomeFinder incomeFinder) {
        this.incomeFinder = incomeFinder;
    }

    @GetMapping("incomes")
    public ResponseEntity<?> findIncomes() {
        var incomes = incomeFinder.findIncomes();
        return new ResponseEntity<>(incomes, HttpStatus.OK);
    }
}
