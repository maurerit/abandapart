package com.aba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by mm66053 on 8/17/2016.
 */
@SpringBootApplication
public class HistoricalAccumulatorApp {
    public static void main ( String[] args )
    {
        ApplicationContext context = SpringApplication.run( HistoricalAccumulatorApp.class, args);
    }
}
