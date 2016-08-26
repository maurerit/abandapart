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

import com.aba.router.IndustryRouter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;

/**
 * Created by maurerit on 8/6/16.
 */
@SpringBootApplication
@EnableAutoConfiguration( exclude = { HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class } )
public class IndustryRouterApp {
    public static void main ( String[] args ) {
        ApplicationContext context = SpringApplication.run( IndustryRouterApp.class, args );
        IndustryRouter router = context.getBean( IndustryRouter.class );
        router.run();
    }
}
