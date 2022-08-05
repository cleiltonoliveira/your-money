package com.yourmoney.persistence.income;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface IncomeRepository extends MongoRepository<IncomeEntity, String> {

   boolean existsByDescriptionAndDateIsBetween(String description, LocalDate startDate, LocalDate endDate);

    Optional<IncomeEntity> findByDescriptionAndDateIsBetween(String description, LocalDate fromDate, LocalDate toDate);
}
