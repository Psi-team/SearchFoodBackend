package com.searchfood.SearchFoodBackend.webapi; 

import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.CrossOrigin; 
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
import com.searchfood.SearchFoodBackend.utils.exceptions.TokenExpiredException; 

@RestController 
@CrossOrigin("*") 
@RequestMapping( value="getStoreTypes", produces="application/json" ) 
public class GetAllMenuController{ 

    private static final Logger log = LoggerFactory.getLogger( GetAllMenuController.class ); 
    private GetFoodTypesImp getFoodTypesImp; 
    private CheckTokenImp checkTokenImp; 

    @Autowired 
    public GetAllMenuController( GetFoodTypesImp g, CheckTokenImp c ){ 
        this.getFoodTypesImp = g; 
        this.checkTokenImp = c; 
    } 

    @GetMapping  
    public ResponseEntity<?> getStoreTypes( @RequestHeader("Authorization") String token ){ 
        
        token = token.substring( token.indexOf(" ")+1 ); 
        log.info( "Processing getStoreTypes..." ); 

        // checking the token is valid or expired. 
        String username = checkTokenImp.check( token ); 
        if( username == null ){ // if the token doesn't exist in database. 
            log.warn( "The token is invalid." ); 
            throw new NotFoundException("The token is invalid."); 
        }else if( username.equals("TokenExpired") ){ 
            log.warn( "The token is out of date." ); 
            throw new TokenExpiredException("Token expired."); 
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

