package com.searchfood.SearchFoodBackend.webapi; 

import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.web.bind.annotation.ResponseBody; 
import org.springframework.web.bind.annotation.RequestHeader; 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import java.util.Map; 
import java.util.List; 

import com.searchfood.SearchFoodBackend.model.GetFoodTypesImp; 
import com.searchfood.SearchFoodBackend.model.CheckTokenImp; 
import com.searchfood.SearchFoodBackend.utils.exceptions.DataFailToLoadedException; 
import com.searchfood.SearchFoodBackend.utils.exceptions.NotFoundException; 

@RestController 
@CrossOrigin("*") 
@RequestMapping( value="getStoreTypes", produces="application/json" ) 
public class CreateStoreTypeController{ 

    private static final Logger log = LoggerFactory.getLogger( CreateStoreTypeController.class ); 
    private GetFoodTypesImp getFoodTypesImp; 
    private CheckTokenImp checkTokenImp; 

    @Autowired 
    public CreateStoreTypeController( GetFoodTypesImp g, CheckTokenImp c ){ 
        this.getFoodTypesImp = g; 
        this.checkTokenImp = c; 
    } 

    @GetMapping  
    public ResponseEntity<?> getStoreTypes( @RequestHeader("Authorization") String token ){ 
        
        log.info( "Processing getStoreTypes..." ); 
        // check the token is valid or not. 
        String username; 
        if( (username = checkTokenImp.check( token )) == null ){ 
            log.info("Invalid Token"); 
            throw new NotFoundException("Invalid token"); 
        } 
        log.info("Valid token"); 
        log.info( "Trying to load the food types from databases..." ); 
        Map<String,List<String>> foodJson = getFoodTypesImp.getFoodTypesMap(); 
        log.debug( "foodJson " + foodJson ); 
        if ( null != foodJson ) 
            return new ResponseEntity<>( foodJson, HttpStatus.OK ); // org.json.JSONObject 無法用ResponseEntity<>傳送, 因此只好用Map來包裝直接傳送回去 
        throw new DataFailToLoadedException( "Cannot access the food types." ); 
    } 

} 

