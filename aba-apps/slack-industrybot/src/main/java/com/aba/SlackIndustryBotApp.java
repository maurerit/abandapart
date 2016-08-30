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

import com.aba.industry.bot.SlackIndustryBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

/**
 * Created by maurerit on 8/7/16.
 */
@SpringBootApplication
//TODO: For now exclude these.  Build in the functionality to administer things into this bot.
@EnableAutoConfiguration( exclude = { HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class } )
public class SlackIndustryBotApp {
    public static void main ( String[] args ) {

        String authToken = System.getenv( "AUTH_TOKEN" );

        if ( authToken == null || "".equalsIgnoreCase( authToken ) ) {
            throw new RuntimeException( "AUTH_TOKEN environment variable can not be null" );
        }

        String[] newArgs = Arrays.copyOf( args, args.length + 1 );
        newArgs[newArgs.length - 1] = "--aba.industry.bot.authToken=" + authToken;

        ApplicationContext context = SpringApplication.run( SlackIndustryBotApp.class, newArgs );
        SlackIndustryBot bot = context.getBean( SlackIndustryBot.class );

        bot.run();
    }
}
