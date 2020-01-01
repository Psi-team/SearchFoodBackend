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

import java.util.Map; 

import com.searchfood.SearchFoodBackend.utils.exceptions.DataNotFoundException; 
import com.searchfood.SearchFoodBackend.utils.CheckTokensController; 
import com.searchfood.SearchFoodBackend.model.data.StoreInfo; 
import com.searchfood.SearchFoodBackend.model.SearchStoreDetailImp; 

@RestController 
@CrossOrigin("*") 
@RequestMapping(value="/storeDetail", produces="application/json") 
public class SearchStoreDetailController{ 

    private final static Logger log = LoggerFactory.getLogger( SearchStoreDetailController.class ); 

    private String username; 
    private CheckTokensController checkTokensController; 
    private Map<String,Object> result; 
    private SearchStoreDetailImp searchStoreDetailImp; 

    public SearchStoreDetailController( CheckTokensController c, SearchStoreDetailImp searchStoreDetailImp ){ 
        this.checkTokensController = c; 
        this.searchStoreDetailImp = searchStoreDetailImp; 
    } 

    @GetMapping  
    public ResponseEntity<?> getStoreDetail( 
            @RequestHeader("Authorization") String token, 
            @RequestParam(value="store_id") int storeId ){ 

        username = checkTokensController.check( token ); 

        log.debug("Store Id is " + storeId ); 

        if ( (this.result = searchStoreDetailImp.fetchStoreDetail( storeId, username )) != null ){ 
            log.debug("results: " + this.result ); 
            return new ResponseEntity( this.result, HttpStatus.OK );  
        }
        
        throw new DataNotFoundException("Data access problems."); 

    } 


} 

