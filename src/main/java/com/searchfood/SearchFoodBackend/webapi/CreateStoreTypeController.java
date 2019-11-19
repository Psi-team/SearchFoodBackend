package com.searchfood.SearchFoodBackend.webapi; 

import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.web.bind.annotation.ResponseBody; 

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import org.json.JSONArray; 

import com.searchfood.SearchFoodBackend.model.FoodTypesImp; 
import com.searchfood.SearchFoodBackend.utils.exceptions.DataFailToLoadedException; 

@RestController 
@CrossOrigin("*") 
@RequestMapping( value="getStoreTypes", produces="application/json" ) 
public class CreateStoreTypeController{ 

    private static final Logger log = LoggerFactory.getLogger( CreateStoreTypeController.class ); 
    private FoodTypesImp foodTypesImp;  

    public CreateStoreTypeController(){ 
    } 

    @GetMapping  
    public ResponseEntity<?> getStoreTypes(){ 
        log.info( "Trying to load the food type from databases..." ); 
        JSONArray foodJson = foodTypesImp.getFoodTypesJson(); 
        if ( null != foodJson ) return new ResponseEntity<>( foodJson, HttpStatus.OK ); 
        throw new DataFailToLoadedException( "Cannot access the food types." ); 
    } 

} 

