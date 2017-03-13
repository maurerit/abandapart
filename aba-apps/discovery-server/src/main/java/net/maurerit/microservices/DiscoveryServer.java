package net.maurerit.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by MM66053 on 2/27/2017.
 */
@SpringBootApplication
@EnableEurekaServer
@EnableDiscoveryClient
public class DiscoveryServer {
    public static void main ( String[] args ) {
        SpringApplication.run( DiscoveryServer.class, args );
    }
}
