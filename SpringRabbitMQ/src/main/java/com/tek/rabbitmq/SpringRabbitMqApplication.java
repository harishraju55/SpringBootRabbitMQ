package com.tek.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringRabbitMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRabbitMqApplication.class, args);
	}
	
		
	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
