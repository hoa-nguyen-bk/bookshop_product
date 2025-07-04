package com.cybersoft.bookshop_product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

@SpringBootApplication
public class BookshopProductApplication {
	@LoadBalanced
	public static void main(String[] args) {
		SpringApplication.run(BookshopProductApplication.class, args);
	}

}
