package com.aba.industry.director;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class IndustryDirectorModule {
	public static void main ( String[ ] args ) {
		@SuppressWarnings("unused")
		ApplicationContext ctx = SpringApplication.run(IndustryDirectorModule.class, args);
	}
}
