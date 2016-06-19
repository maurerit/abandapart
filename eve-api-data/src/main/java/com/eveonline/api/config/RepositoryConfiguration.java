package com.eveonline.api.config;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.eveonline.api.repo.ApiDataRepositoryPackage;

@Configuration
@EntityScan(basePackages = {"com.eveonline.api"})
@EnableJpaRepositories(basePackageClasses = ApiDataRepositoryPackage.class)
@EnableTransactionManagement
public class RepositoryConfiguration {
}
