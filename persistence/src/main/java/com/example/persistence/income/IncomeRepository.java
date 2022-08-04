package com.example.persistence.income;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;

public interface IncomeRepository extends MongoRepository<IncomeEntity, String> {

   boolean existsByDescriptionAndDateIsBetween(String description, LocalDate startDate, LocalDate endDate);
}
