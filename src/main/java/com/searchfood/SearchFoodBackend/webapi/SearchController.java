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

import javax.servlet.http.HttpServletRequest; 

import com.searchfood.SearchFoodBackend.model.SearchStoresImp; 
import com.searchfood.SearchFoodBackend.model.data.StoreInfo; 
import com.searchfood.SearchFoodBackend.utils.exceptions.DataNotFoundException; 
import com.searchfood.SearchFoodBackend.utils.CheckTokensController; 
import com.searchfood.SearchFoodBackend.properties.SearchPageSizeProperties; 

@RestController 
@CrossOrigin("*") 
@RequestMapping(value="/search", produces="application/json") 
public class SearchController{ 

    private static final Logger log = LoggerFactory.getLogger( SearchController.class ); 
    private SearchStoresImp searchStoresImp; 
    private List<Map<String,Object>> resultsList; 
    private CheckTokensController checkTokensController; 
    private SearchPageSizeProperties pageSize; 

    @Autowired 
    public SearchController(SearchStoresImp searchStore, CheckTokensController c, SearchPageSizeProperties s ){ 
        this.searchStoresImp = searchStore; 
        this.checkTokensController= c; 
        this.pageSize = s; 
    } 

    @GetMapping 
    public ResponseEntity<?> getSearchResults( 
                               // @RequestHeader("Authorization") String token, 
                                @RequestParam(value="foodType") String foodKeyWord, 
                                @RequestParam(value="city") String city,
                                @RequestParam(value="district") String district, 
				HttpServletRequest request ) 
                                    throws UnsupportedEncodingException{ // throws exception due to URLDecoder.decode() 
	System.out.println( "HttpServletRequest: " ); 
	System.out.println( request.getMethod() ); 

        foodKeyWord = URLDecoder.decode( foodKeyWord, "utf-8" ); 
        city = URLDecoder.decode( city, "utf-8" ); 
        district = URLDecoder.decode( district, "utf-8" ); 
        log.debug("foodKeyWords: " + foodKeyWord + ", city: " + city + ", district: " + district ); 
        //checkTokensController.check( token ); 
        //log.info("Valid token"); 

        /* These if else may be replaced by @Validated in function arguements */ 
        if( city.equals("") && !district.equals("") || city.equals("") && district.equals("") && foodKeyWord.equals("") ){ // errors with no city but district only.  

            log.debug("Bad Request"); 
            return new ResponseEntity(HttpStatus.BAD_REQUEST); 

        }else if( foodKeyWord.equals("") && !city.equals("") && district.equals("") ){ // search by city only.

            log.debug("Search by city.");  
            resultsList = searchStoresImp.getSearchByCity( city ); 

        }else if( !foodKeyWord.equals("") && city.equals("") && district.equals("") ){ // search by Food only. 

            log.debug("Search by Food Key Word.");  
            resultsList = searchStoresImp.getSearchByFoodKeyWord( foodKeyWord ); 

        }else if( !foodKeyWord.equals("") && !city.equals("") && !district.equals("") ){ // search by food and detail location. 

            log.debug("Search by food and location.");  
            resultsList = searchStoresImp.getSearchByFoodTypeWithDetailLocation( foodKeyWord, city, district ); 

        }else if( !foodKeyWord.equals("") && !city.equals("") && district.equals("") ){ // search by food with city. 

            log.debug("Search by food with city."); 
            resultsList = searchStoresImp.getSearchByFoodTypeWithCity( foodKeyWord, city ); 

        }else if( foodKeyWord.equals("") && !city.equals("") && !district.equals("") ){ // search by detail location only. 

            log.debug("Search by detail logcation only."); 
            resultsList = searchStoresImp.getSearchByDetailLocation( city, district ); 

        }else{ 
            log.debug("Bad Request"); 
            return new ResponseEntity(HttpStatus.BAD_REQUEST);  
        } 

        if ( null != resultsList ) return new ResponseEntity( resultsList, HttpStatus.OK ); 
        else throw new DataNotFoundException("No suitable results."); 
    } 

    
    /*
    @GetMapping("properties") 
    public ResponseEntity<?> getSearchResultsProperties(){ 
        // ref: https://gist.github.com/mobynote/595b61d72a1a0363dc80b7eb785faef9
    } 
    */ 
} 

