package com.yourmoney.persistence.expense;

import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
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

    @Aggregation(pipeline = {
            "{$match: { 'date': {$gt : ?0,  $lte: ?1 }}}",
            "{$group: { _id: $category , total: {$sum: {$toDecimal:  $amount}}}}"
    })
    AggregationResults<Document> findMonthExpenseResume(LocalDate startDate, LocalDate endDate);
}
