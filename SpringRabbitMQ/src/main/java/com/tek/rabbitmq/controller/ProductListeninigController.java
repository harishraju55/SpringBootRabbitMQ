package com.tek.rabbitmq.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tek.rabbitmq.model.Product;

@RestController
public class ProductListeninigController {
	
	@Autowired
	RestTemplate restTemplate;
		
	@RabbitListener(queues="tekgroupproducts")
	public void listenProduct(Message message) throws JsonParseException, JsonMappingException, IOException {
		
		byte[] messageInBytes = message.getBody();
		Product product = new ObjectMapper().readValue(new String(messageInBytes), Product.class);
		String url = "http://localhost:8090/v1/app/save";
		restTemplate.postForLocation(url, product);
	}
	
}
