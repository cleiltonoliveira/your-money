package com.yourmoney.web.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "com.yourmoney")
@EnableMongoRepositories(basePackages = "com.yourmoney.persistence")
@EntityScan(basePackages = "com.yourmoney.persistence")
public class WebTestApplication {

}