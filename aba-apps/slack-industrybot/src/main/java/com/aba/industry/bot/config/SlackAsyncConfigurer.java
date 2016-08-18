/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.bot.config;

import com.aba.industry.bot.async.SlackPostingAsyncUncaughtExceptionHandler;
import com.aba.industry.bot.responder.impl.BuildCalculationRequestResponder;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Created by maurerit on 8/10/16.
 */
@Configuration
@EnableAsync
public class SlackAsyncConfigurer extends AsyncConfigurerSupport {
    @Bean
    public BuildCalculationRequestResponder buildCalculationRequestResponder ( ) {
        return new BuildCalculationRequestResponder();
    }

    @Override
    public Executor getAsyncExecutor ( ) {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize( 2 );
        pool.setMaxPoolSize( 20 );
        pool.setQueueCapacity( 2 );
        return pool;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler ( ) {
        return new SlackPostingAsyncUncaughtExceptionHandler();
    }
}
