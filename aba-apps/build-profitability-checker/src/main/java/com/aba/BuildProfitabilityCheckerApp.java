/*
 * Copyright 2017 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba;

import com.aba.industry.BuildProfitabilityCheckerReceiver;
import com.aba.industry.model.BuildCalculationResult;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by maurerit on 1/27/17.
 */
@SpringBootApplication
public class BuildProfitabilityCheckerApp {
    public static final String queueName = "build-checker";

    public static void main ( String[] args ) throws InterruptedException {
        SpringApplication.run( BuildProfitabilityCheckerApp.class, args );
    }

    @Bean
    Queue queue ( ) {
        return new Queue( queueName, false );
    }

    @Bean
    FanoutExchange exchange ( ) {
        //TODO: Make this a constant somewhere
        return new FanoutExchange( "build-calc" );
    }

    @Bean
    Binding binding ( Queue queue, FanoutExchange topicExchange ) {
        return BindingBuilder.bind( queue )
                             .to( topicExchange );
    }

    @Bean
    SimpleMessageListenerContainer container ( ConnectionFactory connectionFactory,
                                               MessageListenerAdapter listenerAdapter )
    {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory( connectionFactory );
        container.setQueueNames( queueName );
        container.setMessageListener( listenerAdapter );
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter ( BuildProfitabilityCheckerReceiver receiver ) {
        JsonMessageConverter messageConverter = new JsonMessageConverter();
        DefaultClassMapper defaultJavaTypeMapper = new DefaultClassMapper();
        defaultJavaTypeMapper.setDefaultType( BuildCalculationResult.class );
        messageConverter.setClassMapper( defaultJavaTypeMapper );
        return new MessageListenerAdapter( receiver, messageConverter );
    }
}
