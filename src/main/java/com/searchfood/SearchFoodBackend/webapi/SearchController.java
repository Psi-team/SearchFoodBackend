// use @ConfigurationProperties to set the Searching properties
// See PP.122 on Spring in Action. Ch5.2 

package com.searchfood.SearchFood.webapi; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestParam; //import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.http.ResponseEntity; 
import org.springframework.http.HttpStatus; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import java.util.Map; 

@RestController 
@CrossOrigin("*") 
@RequestMapping(value="/Search") 
public class SearchController{ 

    private static final Logger log = LoggerFactory.getLogger( SearchController.class ); 

    public SearchController(){ 
    } 

    @GetMapping 
    public ResponseEntity<?> getSearchResults( @RequestParam(value="foodType") String foodType, 
                                               @RequestParam(value="City") String City,
                                               @RequestParam(value="District") String District ){ 

        log.debug("foodTypes: " + foodType ); 
        log.debug("City: " + City ); 
        log.debug("District: " + District ); 

        return new ResponseEntity<>( City, HttpStatus.OK ); 
    } 
} 

