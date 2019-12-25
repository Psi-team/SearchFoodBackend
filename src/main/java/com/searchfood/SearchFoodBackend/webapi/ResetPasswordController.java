package com.searchfood.SearchFoodBackend.webapi; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.CrossOrigin; 

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

@RestController
@CrossOrigin("*") 
@RequestMapping( value="ResetPassword" ) 
public class ResetPasswordController{ 

    private final static Logger log = LoggerFactory.getLogger( ResetPasswordController.class ); 

    @Autowired 
    public ResetPasswordController(){ 

    } 

} 
