// use @ConfigurationProperties to set the Searching properties
// See PP.122 on Spring in Action. Ch5.2 
package com.searchfood.SearchFoodBackend.webapi; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestHeader; 
import org.springframework.web.bind.annotation.RequestParam; //import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.http.ResponseEntity; 
import org.springframework.http.HttpStatus; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import java.util.List; 
import java.util.Map; 
import java.util.HashMap; 

import java.net.URLDecoder; 
import java.io.UnsupportedEncodingException; 

import com.searchfood.SearchFoodBackend.model.SearchStoresImp; 
import com.searchfood.SearchFoodBackend.model.data.StoreInfo; 
import com.searchfood.SearchFoodBackend.utils.exceptions.DataNotFoundException; 
import com.searchfood.SearchFoodBackend.utils.CheckTokensController; 

@RestController 
@CrossOrigin("*") 
@RequestMapping(value="/search", produces="application/json") 
public class SearchController{ 

    private static final Logger log = LoggerFactory.getLogger( SearchController.class ); 
    private SearchStoresImp searchStoresImp; 
    private List<Map<String,Object>> resultsList; 
    private CheckTokensController checkTokensController; 

    @Autowired 
    public SearchController(SearchStoresImp searchStore, CheckTokensController c ){ 
        this.searchStoresImp = searchStore; 
        this.checkTokensController= c; 
    } 

    @GetMapping 
    public ResponseEntity<?> getSearchResults( 
//                                @RequestHeader("Authorization") String token, 
                                @RequestParam(value="foodType") String foodKeyWord, 
                                @RequestParam(value="city") String city,
                                @RequestParam(value="district") String district ) throws UnsupportedEncodingException{ 

        foodKeyWord = URLDecoder.decode( foodKeyWord, "utf-8" ); 
        city = URLDecoder.decode( city, "utf-8" ); 
        district = URLDecoder.decode( district, "utf-8" ); 
        log.debug("foodKeyWords: " + foodKeyWord + ", city: " + city + ", district: " + district ); 
        //checkTokensController.check( token ); 
        //log.info("Valid token"); 

        /* These if else may be replaced by @Validated */ 
        if( city.equals("") && !district.equals("") || !city.equals("") && district.equals("") && foodKeyWord.equals("") ){ // errors with no city but district only.  

            return new ResponseEntity(HttpStatus.BAD_REQUEST); 

        }else if( foodKeyWord.equals("") && !city.equals("") ){ // search by location. 

            log.debug("Search by Locations.");  
            resultsList = searchStoresImp.getSearchByLocation( city, district ); 

        }else if( !foodKeyWord.equals("") && city.equals("") ){ // search by Food. 

            log.debug("Search by Food Key Word.");  
            resultsList = searchStoresImp.getSearchByFoodKeyWord( foodKeyWord ); 

        }else if( !foodKeyWord.equals("") && !city.equals("") ){ // search by food and location. 

            log.debug("Search by food and location.");  
            resultsList = searchStoresImp.getSearchByFoodTypeWithLocation( foodKeyWord, city, district ); 

        }else{ 
            return new ResponseEntity(HttpStatus.BAD_REQUEST);  
        } 

        if ( null != resultsList ) return new ResponseEntity( resultsList, HttpStatus.OK ); 
        else throw new DataNotFoundException("No suitable results."); 
    } 
} 

