package com.yourmoney.persistence.expense;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends MongoRepository<ExpenseEntity, String> {

   boolean existsByDescriptionAndDateIsBetween(String description, LocalDate startDate, LocalDate endDate);

    Optional<ExpenseEntity> findByDescriptionAndDateIsBetween(String description, LocalDate fromDate, LocalDate toDate);

    List<ExpenseEntity> findAllByDescriptionContainingIgnoreCase(String description);

    @Query("{'date' : { $gte: ?0, $lte: ?1 } }")
    List<ExpenseEntity> findAllByDateBetween(LocalDate startDate, LocalDate endDate);
}
