package com.tek.rabbitmq.controller;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tek.rabbitmq.model.Product;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value="/v1/app")
@Api(value="stocksApp", description="This service is for crud operation for StockApp")
public class ProductsController {
	
	private static final String PROCESS_QUEUE = "tekgroupproducts";

	
	
	@Autowired
	private RabbitTemplate rabbitTemplate;


	@RequestMapping(value="/product", method=RequestMethod.POST)
	public void processProduct(@RequestBody Product product) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(product);
		String productString = mapper.writeValueAsString(product);
		System.out.println(productString+"is here");
		rabbitTemplate.convertAndSend(PROCESS_QUEUE, productString);


	}
}
