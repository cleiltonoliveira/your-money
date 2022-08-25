package com.yourmoney.persistence.income;

import org.bson.types.Decimal128;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IncomeRepository extends MongoRepository<IncomeEntity, String> {

//    @Query(value = "{'description' : ?0, 'date' : { $gte : { '$date' : ?1}, $lte : { '$date' : ?2 }}}", count = true)
    boolean existsByDescriptionAndDateBetween(String description, LocalDate startDate, LocalDate endDate);

    Optional<IncomeEntity> findByDescriptionAndDateIsGreaterThanEqualAndDateIsLessThanEqual(String description, LocalDate fromDate, LocalDate toDate);

    List<IncomeEntity> findAllByDescriptionContainingIgnoreCase(String description);

    @Query("{'date' : { $gte: ?0, $lte: ?1 } }")
    List<IncomeEntity> findAllByDateBetween(LocalDate startDate, LocalDate endDate);

    @Aggregation(pipeline = {
            "{$match: { 'date': {$gt : ?0,  $lte: ?1 }}}",
            "{$group: { _id: '', total: {$sum: {$toDecimal:  $amount}}}}"
    })
    Decimal128 findMonthIncomeAmount(LocalDate startDate, LocalDate endDate);
}
