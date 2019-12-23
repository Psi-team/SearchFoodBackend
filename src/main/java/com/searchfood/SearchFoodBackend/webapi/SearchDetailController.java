package com.searchfood.SearchFoodBackend.webapi; 

import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.web.bind.annotation.RequestHeader; 
import org.springframework.web.bind.annotation.RequestParam; 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.http.ResponseEntity; 
import org.springframework.http.HttpStatus; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

@RestController 
@CrossOrigin("*") 
@RequestMapping(value="/storeDetail", produces="application/json") 
public class SearchDetailController{ 

    private final static Logger log = LoggerFactory.getLogger( SearchDetailController.class ); 

    @GetMapping  
    public ResponseEntity<?> getStoreDetail( 
            @RequestHeader("Authorization") String token, 
            @RequestParam(value="store_id") int storeId ){ 

            log.debug("Store Id is " + storeId ); 

            return new ResponseEntity( storeId, HttpStatus.OK );  

    } 


} 

