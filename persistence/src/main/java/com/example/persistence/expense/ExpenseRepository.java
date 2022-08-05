package com.example.persistence.expense;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ExpenseRepository extends MongoRepository<ExpenseEntity, String> {

   boolean existsByDescriptionAndDateIsBetween(String description, LocalDate startDate, LocalDate endDate);

    Optional<ExpenseEntity> findByDescriptionAndDateIsBetween(String description, LocalDate fromDate, LocalDate toDate);
}
