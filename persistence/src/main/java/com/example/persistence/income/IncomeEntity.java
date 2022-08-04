package com.example.persistence.income;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Document("incomes")
@Data
public class IncomeEntity {
    @Id
    private String id;

    @NotBlank
    private String description;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private LocalDate date;
}
