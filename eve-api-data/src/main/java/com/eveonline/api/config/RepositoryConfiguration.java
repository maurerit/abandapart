package com.eveonline.api.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.eveonline.api"})
@EnableJpaRepositories(basePackages = {"com.eveonline.api.repo"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
