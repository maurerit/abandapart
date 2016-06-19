package com.eveonline.api;

import com.eveonline.api.repo.ApiDataRepositoryPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses=ApiDataRepositoryPackage.class)
public class TestApplication {

}
