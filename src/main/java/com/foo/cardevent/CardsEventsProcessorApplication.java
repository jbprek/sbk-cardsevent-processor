package com.foo.cardevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class CardsEventsProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsEventsProcessorApplication.class, args);
	}

}
