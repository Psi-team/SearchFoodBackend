package com.searchfood.SearchFoodBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties; 

import org.springframework.core.SpringVersion; 

import com.searchfood.SearchFoodBackend.properties.FileStorageProperties; 

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class 
})
public class SearchFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchFoodApplication.class, args);
        System.out.println("Spring Framework version: " + SpringVersion.getVersion()); 
	}

}
