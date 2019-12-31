package com.searchfood.SearchFoodBackend.properties;

import org.springframework.context.annotation.Configuration; 
import org.springframework.context.annotation.Bean; 

import org.springframework.web.client.RestTemplate; 

import org.springframework.jdbc.core.JdbcTemplate; 

import com.searchfood.SearchFoodBackend.model.GetFoodTypesImp; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

@Configuration 
public class SearchFoodConfig{ 

    private static final Logger log = LoggerFactory.getLogger( SearchFoodConfig.class ); 

    /* 
    @Bean 
    public GetFoodTypesImp getGetFoodTypesImp( JdbcTemplate jdbc ){ 
        log.info("@Configuration: create GetFoodTypesImp Bean."); 
        return new GetFoodTypesImp( jdbc );  
    } 
    */ 

    @Bean 
    public RestTemplate getRestTemplate(){ 
        System.out.println(" Initializing a RestTemplate object."); 
        return new RestTemplate(); 
    } 

} 

