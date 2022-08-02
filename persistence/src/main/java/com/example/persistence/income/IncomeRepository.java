package com.example.persistence.income;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface IncomeRepository extends MongoRepository<IncomeEntity, Long> {

}
