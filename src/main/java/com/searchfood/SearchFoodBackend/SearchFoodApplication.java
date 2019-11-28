package com.searchfood.SearchFoodBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.core.SpringVersion; 

@SpringBootApplication
public class SearchFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchFoodApplication.class, args);
        System.out.println("Spring Framework version: " + SpringVersion.getVersion()); 
	}

}
