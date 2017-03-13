/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba;

import com.aba.industry.AutoBuildChecker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by maurerit on 8/27/16.
 */
@SpringBootApplication
@EnableAutoConfiguration( exclude = { HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class } )
@EnableCaching
@EnableScheduling
public class AutoBuildCheckerApp {
    public static void main ( String[] args ) throws InterruptedException {
        ApplicationContext context = SpringApplication.run( AutoBuildCheckerApp.class, args );
        AutoBuildChecker checker = context.getBean( AutoBuildChecker.class );

        while ( true ) {
            Thread.sleep( 10000 );
        }
    }
}
