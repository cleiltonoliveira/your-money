package com.yourmoney.persistence.income;

import org.bson.types.Decimal128;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IncomeRepository extends MongoRepository<IncomeEntity, String> {

    @Query(value = "{'description' : ?0, 'date' : { $gte: ?1, $lte: ?2 } }", exists = true)
    boolean existsByDescriptionAndDateBetween(String description, LocalDate startDate, LocalDate endDate);

    @Query(value = "{'description' : ?0, 'date' : { $gte: ?1, $lte: ?2 } }")
    Optional<IncomeEntity> findByDescriptionAndDateBetween(String description, LocalDate fromDate, LocalDate toDate);

    List<IncomeEntity> findAllByDescriptionContainingIgnoreCase(String description);

    @Query("{'date' : { $gte: ?0, $lte: ?1 } }")
    List<IncomeEntity> findAllByDateBetween(LocalDate startDate, LocalDate endDate);

    @Aggregation(pipeline = {
            "{$match: { 'date': {$gte : ?0,  $lte: ?1 }}}",
            "{$group: { _id: '', total: {$sum: {$toDecimal:  $amount}}}}"
    })
    Decimal128 findMonthIncomeAmount(LocalDate startDate, LocalDate endDate);
}
