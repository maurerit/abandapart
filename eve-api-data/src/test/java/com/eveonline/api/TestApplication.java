package com.eveonline.api;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.eveonline.api.repo.ApiDataRepositoryPackage;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses=ApiDataRepositoryPackage.class)
public class TestApplication {

}
