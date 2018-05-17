package com.puzia.bookstore.db.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("com.puzia.bookstore.db.entity")
@EnableJpaRepositories(basePackages = {"com.puzia.bookstore.db.repository"})
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class JpaConfig {

}
