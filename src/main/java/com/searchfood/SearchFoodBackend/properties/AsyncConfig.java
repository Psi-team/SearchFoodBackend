package com.searchfood.SearchFood.properties; 

import org.springframework.context.annotation.Configuration; 
import org.springframework.context.annotation.Bean; 

import org.springframework.stereotype.Component; 

import org.springframework.scheduling.annotation.AsyncConfigurerSupport; 
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor; 
import org.springframework.scheduling.annotation.EnableAsync; 

import java.util.concurrent.Executor; 

@Configuration 
public class AsyncConfig extends AsyncConfigurerSupport{ 

    @Override 
    public Executor getAsyncExecutor(){ 
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor(); 
        executor.setCorePoolSize(2); 
        executor.setMaxPoolSize(5); 
        executor.setQueueCapacity(500); 
        executor.setThreadNamePrefix("the-async-thread-"); 
        executor.initialize(); 

        return executor; 
    } 

    @Bean 
    public Executor getExecutor(){ 
        System.out.println("*************** Async Config *********************"); 
        return getAsyncExecutor(); 
    } 

} 
// This Config class seems doese not work. 


