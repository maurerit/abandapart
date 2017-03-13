package net.maurerit.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by mm66053 on 2/28/2017.
 */
@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
public class ShoppingCartApplication {
    public static void main ( String[] args ) {
        SpringApplication.run( ShoppingCartApplication.class, args );
    }

    @Bean
    public Docket shoppingCartApi ( ) {
        return new Docket( DocumentationType.SWAGGER_2 )
                .groupName( "shopping-cart" )
                .apiInfo( apiInfo() )
                .select()
                .paths( regex( "/cart.*" ) )
                .build();
    }

    private ApiInfo apiInfo ( ) {
        return new ApiInfoBuilder()
                .title( "Simple Shopping Cart Service" )
                .description( "Shopping Cart Service with Spring REST and Swagger" )
                .contact( new Contact( "Matt Maurer", "https://github.com/maurerit", "maurer.it@gmail.com" ) )
                .license( "Apache License Version 2.0" )
                .licenseUrl( "https://github.com/maurerit/microservices-playground/blob/master/LICENSE" )
                .version( "1.0" )
                .build();
    }
}
