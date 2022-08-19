package com.yourmoney.persistence.income;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IncomeRepository extends MongoRepository<IncomeEntity, String> {

    boolean existsByDescriptionAndDateIsBetween(String description, LocalDate startDate, LocalDate endDate);

    Optional<IncomeEntity> findByDescriptionAndDateIsBetween(String description, LocalDate fromDate, LocalDate toDate);

    List<IncomeEntity> findAllByDescriptionContainingIgnoreCase(String description);

    @Query("{'date' : { $gte: ?0, $lte: ?1 } }")
    List<IncomeEntity> findAllByDateBetween(LocalDate startDate, LocalDate endDate);
}
