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

import com.searchfood.SearchFoodBackend.utils.exceptions.DataNotFoundException; 
import com.searchfood.SearchFoodBackend.utils.CheckTokensController; 
import com.searchfood.SearchFoodBackend.model.data.StoreInfo; 

@RestController 
@CrossOrigin("*") 
@RequestMapping(value="/storeDetail", produces="application/json") 
public class SearchStoreDetailController{ 

    private final static Logger log = LoggerFactory.getLogger( SearchStoreDetailController.class ); 

    private String username; 
    private CheckTokenImp checkTokenImp; 
    private StoreInfo storeInfo; 

    public SearchStoreDetailController( CheckTokenImp c ){ 
        this.checkTokenImp = c; 
    } 

    @GetMapping  
    public ResponseEntity<?> getStoreDetail( 
            @RequestHeader("Authorization") String token, 
            @RequestParam(value="store_id") int storeId ){ 

        username = checkTokenImp.check( token ); 

        log.debug("Store Id is " + storeId ); 

       if ( storeInfo != null ) 
           return new ResponseEntity( storeId, HttpStatus.OK );  

       throw new DataNotFoundException("Data not found."); 

    } 


} 

