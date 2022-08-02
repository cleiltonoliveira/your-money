package com.example.persistence.income;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Document("incomes")
@Data
public class IncomeEntity {
    @Id
    private String id;

    @NotBlank
    private String description;

    @Email
    @NotBlank
    private String amount;

    @NotNull
    private LocalDateTime date;
}
