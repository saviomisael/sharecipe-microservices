package com.github.saviomisael.gourmetdiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class GourmetDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(GourmetDiscoveryApplication.class, args);
	}

}
